#include <iostream>
#include <vector>
#include <math.h>
#include <fstream>
#include <chrono>
#include <string>

using namespace std;

# define PI 3.14159265358979323846

// funciones para calcular los coeficientes, simplifican la lectura de funciones como armarSist
// j-1, k
double coef1(double r, double deltaRadio){return (1 / deltaRadio) * ((1 / deltaRadio) - (1 / r));}
// j, k-1
double coef2(double r, double deltaTheta){return pow((1 / (deltaTheta * r)), 2);}
// j+1, k
double coef3(double deltaRadio){return pow((1 / deltaRadio), 2);}
// j, k+1
double coef4(double r, double deltaTheta){return pow((1 / (deltaTheta * r)), 2);}
// j, k
double coef5(double r, double deltaRadio, double deltaTheta){return (1 / (deltaRadio * r)) - 2 * (pow((1 / deltaRadio), 2) + pow((1 / (deltaTheta * r)), 2));}

// Dados los datos de la discretizacion, arma la matriz del sistema poniendo los coeficientes correspondientes en sus posiciones
vector<vector<double>> armarSist(int cantRadios, int cantSensores, double deltaRadio, double deltaTheta, int radioInterno){
    int n = (cantRadios-2) * cantSensores;
    vector<double> ceros(n,0);
    vector<vector<double>> res;
    for(int i = 0; i < n; i++){
        res.push_back(ceros);
    }
    for(int i = 0; i < n; i++){
        int j = ((int)i / cantSensores) + 1;
        int k = i % cantSensores;
        double r = radioInterno + deltaRadio * j;

        if(j != 1){
            // aca despejamos c1 al vector independiente
            res[i][i - cantSensores] = coef1(r, deltaRadio);
        }

        if(k == 0){
            res[i][i + cantSensores - 1] = coef2(r, deltaTheta);
        } else {
            res[i][i - 1] = coef2(r, deltaTheta);
        }

        if(j != cantRadios-2){
            // aca despejamos c3 al vector independiente
            res[i][i + cantSensores] = coef3(deltaRadio);
        }

        if(k + 1 == cantSensores){
            res[i][i + 1 - cantSensores] = coef4(r, deltaTheta);
        } else {
            res[i][i + 1] = coef4(r, deltaTheta);
        }

        res[i][i] = coef5(r, deltaRadio, deltaTheta);
    }
    return res;
}

// Dado un vector tempExt con las temperaturas de los sensores externos y otro tempInt con las temperaturas internas multiplica por los coeficientes correspondientes para armar el vector independiente b usado para esa instancia de el sistema
vector<double> armar_b(const vector<double> &tempExt,const vector<double> &tempInt, int cantRadios, int cantSensores, double deltaRadio, int radioInterno){
    int n = (cantRadios - 2) * cantSensores;
    vector<double> res(n,0);
    for(int i = 0; i < cantSensores; i++){
        res[i] = - tempInt[i] * coef1(radioInterno + deltaRadio, deltaRadio);
    }
    for(int i = n - cantSensores; i < n; i++){
        res[i] = - tempExt[i % cantSensores] * coef3(deltaRadio);
    }
    return res;
}

// Eliminacion gaussiana, crea una matriz triangular a partir de una matriz cuadrada (PRECONDICION: la matriz no requiere pivoteo para resolver el sistema)
pair<vector<vector<double>>, vector<double>> elimGauss(const vector<vector<double>> &m, const vector<double> &b, int cantSensores, bool O){
    int n = m.size();
    vector<vector<double>> res = m;
    vector<double> bPrima = b;
    double multji = 1;
    for(int i = 0; i < n-1; i++){
        if(!O){ // codigo que no aprovecha la banda
            for(int j = i + 1; j < n; j++){ // rango de ciclo estandar para EG
                multji = res[j][i]/res[i][i];
                bPrima[j] = bPrima[j] - multji * bPrima[i];
                for(int k = i; k <= n; k++){
                res[j][k] = res[j][k] - multji * res[i][k];
                }
            }
        } else { // codigo que aprovecha la banda
            for(int j = i + 1; j < min(i + cantSensores + 1,n); j++){ //rango de ciclo mas acotado, ver informe
                multji = res[j][i]/res[i][i];
                bPrima[j] = bPrima[j] - multji * bPrima[i];
                for(int k = i; k <= n; k++){
                    res[j][k] = res[j][k] - multji * res[i][k];
                }
            }
        }
    }
    return make_pair(res, bPrima);
}

// LU en matriz unica (PRECONDICION: la matriz no requiere pivoteo para resolver el sistema)
pair<vector<vector<double>>, vector<double>> matLU(const vector<vector<double>> &m, const vector<double> &b, int cantSensores, bool O){
    int n = m.size();
    vector<vector<double>> res = m;
    vector<double> bPrima = b;
    double multji = 1;
    for(int i = 0; i < n-1; i++){
        if(!O){ // codigo que no aprovecha la banda
            for(int j = i + 1; j < n; j++){ // rango de ciclo estandar para LU
                multji = res[j][i]/res[i][i];
                res[j][i] = multji;
                bPrima[j] = bPrima[j] - multji * bPrima[i];
                for(int k = i + 1; k <= n; k++){
                    res[j][k] = res[j][k] - multji * res[i][k];
                }
            }
        } else { // codigo que aprovecha la banda
            for(int j = i + 1; j < min(i + cantSensores + 1,n); j++){ //rango de ciclo mas acotado, ver informe
                multji = res[j][i]/res[i][i];
                res[j][i] = multji;
                bPrima[j] = bPrima[j] - multji * bPrima[i];
                for(int k = i + 1; k <= n; k++){
                    res[j][k] = res[j][k] - multji * res[i][k];
                }
            }
        }
    }
    return make_pair(res, bPrima);
}

// Separa matriz unica LU en el par L, U
pair<vector<vector<double>>, vector<vector<double>>> separarLU(const vector<vector<double>> &m){
    int n = m.size();
    vector<vector<double>> L;
    vector<vector<double>> U;
    vector<double> ceros(n,0);

    // inicializamos L y U en cero
    for(int i = 0; i < n; i++){
        L.push_back(ceros);
        U.push_back(ceros);
    }

    // copiamos la parte L de la matriz unica
    for(int i = 0; i < n; i++){
        L[i][i] = 1;
        for(int j = 0; j < i; j++){
            L[i][j] = m[i][j];
        }
    }

    // copiamos la parte U de la matriz unica
    for(int i = 0; i < n; i++){
        for(int j = i; j < n; j++){
            U[i][j] = m[i][j];
        }
    }
    return make_pair(L, U);
}

// Imprime una matriz cuadrada en pantalla
void prtMat(const vector<vector<double>> &m){
    int n = m.size();
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n - 1 ; j++){
            cout << m[i][j] << ", ";
        }
        cout << m[i][n-1] << endl;
    }
    cout << endl;
}

// Imprime un vector en pantalla
void prtVect(const vector<double> &v){
    int n = v.size();
    cout << '[';
    for(int i = 0; i < n - 1 ; i++){
        cout << v[i] << ", ";
    }
    cout << v[n-1] << ']' << endl;
    cout << endl;
}

// Exporta una matriz cuadrada en un archivo csv
void exportMat(const vector<vector<double>> &m, string nombre){
    fstream CreateFile("../csv/" + nombre, ios::out);
    int n = m.size();
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n - 1 ; j++){
            CreateFile << m[i][j] << ",";
        }
        CreateFile << m[i][n-1] << endl;
    }
}

// Exporta un vector en un archivo csv
void exportVect(const vector<double> &v, string nombre) {
    int n = v.size();
    fstream CreateFile("../csv/" + nombre, ios::out);
    for (int i = 0; i < n - 1; i++) {
        CreateFile << v[i] << ",";
    }
    CreateFile << v[n - 1] << endl;
}

// Dado un sistema triangulado superior valido, devuelve su vector solucion
vector<double> resolverSist(const pair<vector<vector<double>>, vector<double>> &sist){
    int n = sist.second.size();
    vector<double> res(n, 0);
    double acum = 0;
    for(int i = n - 1; i >= 0; i--){
        acum = 0;
        for(int j = i + 1; j < n; j++){
            acum += sist.first[i][j] * res[j];
        }
        res[i] = (sist.second[i] - acum) / sist.first[i][i];
    }
    return res;
}

// Dado un sistema triangulado inferior valido, devuelve su vector solucion
vector<double> resolverSistInf(const pair<vector<vector<double>>, vector<double>> &sist){
    int n = sist.second.size();
    vector<double> res(n, 0);
    double acum = 0;
    for(int i = 0; i < n; i++){
        acum = 0;
        for(int j = 0; j < i; j++){
            acum += sist.first[i][j] * res[j];
        }
        res[i] = (sist.second[i] - acum) / sist.first[i][i];
    }
    return res;
}

// Dada una factorizacion LU (el par L,U) de un sistema y su vector independiente, resuelve el sistema (resolviendo 2 sistemas triangulares)
vector<double> resolverSistLU(const pair<vector<vector<double>>, vector<vector<double>>> &LU, const vector<double> &b){
    vector<double> y = resolverSistInf(make_pair(LU.first, b)); //Ly=b
    vector<double> res = resolverSist(make_pair(LU.second, y)); //Ux=y
    return res; //x
}

// Dado un vector que representa las temperaturas de los puntos intermedios (antes incognitas) del sistema, uno que representa las externas y otro las internas devuelve un vector que concatena las temperaturas talque primero se encuentren las internas, luego intermedias y finalmente las externas
vector<double> agregarTempExtYInt(const vector<double> &temp, const vector<double> &tempExt, const vector<double> &tempInt, int cantRadios, int cantSensores){
    int n = cantRadios*cantSensores;
    vector<double> res(cantRadios*cantSensores,0);
    for(int i = 0; i < res.size(); i++){
        if(i < cantSensores) res[i]=(tempInt[i]);
        if(i >= cantSensores && i < n-cantSensores) res[i]=(temp[i-cantSensores]);
        if(i >= n-cantSensores) res[i]=(tempExt[i%cantSensores]);
    }
    return res;
}

int main(int argc, char **argv){
    bool EG = false; // modificar este bool para compilar LU (false) o EG (true), para compilar LUO o EGO modificar esta linea y la siguiente
    bool O = true; // modificar este bool para compilar las versiones optimizadas de EG o LU (EGO o LUO respectivamente)

    // para simplificar conversiones, pasamos cada argumento a un string (queda un vector de argc strings)
    vector<string> argvst;
    for(int i = 0; i < argc; i++) argvst.push_back(argv[i]);

    // datos para discretizacion
    int radioInterno = stoi(argvst[1]); //Ri
    int radioExterno = stoi(argvst[2]); //Re
    int cantRadios = stoi(argvst[3]); // m+1
    int cantSensores = stoi(argvst[4]); // n
    double valorIsoterma = stof(argvst[5]); // Valor de isoterma buscada

    int ninst = stoi(argvst[6]); // cantidad de instancias del problema a resolver para los parametros dados

    vector<vector<double>> tempExt(ninst); // tiene las temperaturas externas de todas las instancias (tExt[i][j] temp ext del sensor j en instancia i)
    vector<vector<double>> tempInt(ninst); // tiene las temperaturas internas de todas las instancias (tExt[i][j] temp int del sensor j en instancia i)
    for(int i = 0; i < ninst; i++){
        for (int j = 0; j < 2 * cantSensores; j++){
            if(j < cantSensores) tempInt[i].push_back(stod(argvst[7 + i * (2 * cantSensores) + j]));
            else tempExt[i].push_back(stod(argvst[7 + i * (2 * cantSensores) + j]));
        }
    }

    // datos de la discretizacion para resolver las ecuaciones
    double deltaTheta = 2 * PI / (double) cantSensores; // distancia entre cada angulo posible en la discretizacion actual del sistema
    double deltaRadio = (double) (radioExterno - radioInterno) / (double) (cantRadios-1);

    vector<vector<double>> m = armarSist(cantRadios, cantSensores, deltaRadio, deltaTheta, radioInterno); // representa la matriz unica del sistema a resolver (es una aunque ninst > 1)
    vector<vector<double>> b(ninst); // representa los ninst vectores independientes (uno por cada instancia) del sistema
    for(int i = 0; i < ninst; i++) b[i] = armar_b(tempExt[i], tempInt[i], cantRadios, cantSensores, deltaRadio, radioInterno);

    vector<vector<double>> temp(ninst);

    auto start = chrono::steady_clock::now();
    // resolver con EG o LU dependiendo del bool
    if (EG){// EG
        vector<pair<vector<vector<double>>, vector<double>>> sist(ninst);
        for(int i = 0; i < ninst; i++) sist[i] = elimGauss(m,b[i],cantSensores,O);
        for(int i = 0; i < ninst; i++) temp[i] = resolverSist(sist[i]);
    } else {
        // LU
        pair<vector<vector<double>>, vector<double>> sistLU = matLU(m, b[0],cantSensores,O);
        pair<vector<vector<double>>, vector<vector<double>>> LU = separarLU(sistLU.first);
        temp[0] = resolverSist(make_pair(LU.second, sistLU.second));
        for(int i = 1; i < ninst; i++) temp[i] = resolverSistLU(LU, b[i]);
    }
    auto end = chrono::steady_clock::now();

    string nombreAlgoritmo;
    if(EG) nombreAlgoritmo = "EG";
    else nombreAlgoritmo = "LU";
    if(O) nombreAlgoritmo += "O";
    // imprimir los ninst resultados en ninst csv's,
    for(int i = 0; i < ninst; i++){
        exportVect(agregarTempExtYInt(temp[i], tempExt[i], tempInt[i], cantRadios, cantSensores), "temp" + nombreAlgoritmo + to_string(i) + ".csv");
    }

    // mostrar tiempo de EG/LU
    cout << "Elapsed time in milliseconds: "
        << chrono::duration_cast<chrono::milliseconds>(end - start).count()
        << " ms" << endl;

}
