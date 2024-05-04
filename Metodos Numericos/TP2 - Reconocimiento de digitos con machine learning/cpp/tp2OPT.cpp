#include <iostream>
#include <vector>
#include <math.h>
#include <fstream>
#include <chrono>
#include <string>
#include <sstream>
#include <Eigen> //aca hay q instalar los headers, los puse en la carpeta del git, ver Makefile

using namespace std;
using namespace Eigen;

int numeroMaximoDeIteraciones = 10000;

int decision(const vector<pair<int, double>> &kMasCercanos, int k, bool votacionPesada){
    if (votacionPesada){
        // le damos el peso a cada vecino, luego armamos el vector contador pero esta vez lo que contamos es el peso de cada clase, luego gana la que tenga mas peso
        vector<double> promNorm2(10,0);
        vector<int> count(10,0);
        for(int i = 0; i < k; i++){
            promNorm2[kMasCercanos[i].first] += kMasCercanos[i].second;
            count[kMasCercanos[i].first]++;
        }
        for(int i = 0; i < 10; i++) if(count[i] != 0) promNorm2[i] /= double (count[i]); // ojo con dividir por 0
        pair<int, double> min = make_pair(0, promNorm2[0]);
        for(int i = 1; i < 10; i++) if(count[min.first] == 0 || (min.second > promNorm2[i] && count[i] > 0)) min = make_pair(i, promNorm2[i]);
        return min.first;
    } else {
        vector<int> count(10,0); // vector contador para cada clase
        for(int i = 0; i < k; i++) count[kMasCercanos[i].first]++; // cuento las apariciones de cada clase
        pair<int, int> max = make_pair(0, count[0]);
        for(int i = 1; i < 10; i++) if(max.second < count[i]) max = make_pair(i, count[i]); // criterio de desempate actual = me quedo con el digito mas chico
        return max.first; // decido la clase que tenga mas apariciones en kMasCercanos
    }
}

int kNN(const VectorXd &digAct, int k, bool votacionPesada, const MatrixXd &baseEntrenamiento, const VectorXi &indicesEntrenamiento, vector<vector<pair<int, double>>> &guardarKMasCercanos, bool guardarKMasCercanosBool){
    vector<pair<int, double>> kMasCercanos(k);
    double norma2;
    for(int i = 0; i < baseEntrenamiento.rows(); i++){
        norma2 = (digAct - VectorXd (baseEntrenamiento.row(i))).norm();  // calculamos la norma 2 de la resta entre el digito actual y cada elemento de train
        if(i >= k){
            if(kMasCercanos[k-1].second > norma2){  // me fijo si tengo q agregarlo, si lo agrego lo agrego al final y luego reoordenar
                kMasCercanos[k-1] = make_pair(indicesEntrenamiento(i), norma2);
                for(int j = k - 1; j > 0 && kMasCercanos[j-1].second > kMasCercanos[j].second; j--) swap(kMasCercanos[j], kMasCercanos[j-1]); // ordenamos en O(k)
            }
        } else {
            kMasCercanos[i] = make_pair(indicesEntrenamiento(i), norma2);  // como todavia no tenemos k elementos lo agregamos de forma ordenada
            for(int j = i; j > 0 && kMasCercanos[j-1].second > kMasCercanos[j].second; j--) swap(kMasCercanos[j], kMasCercanos[j-1]); // ordenamos en O(k)
        }
    }
    if(guardarKMasCercanosBool) guardarKMasCercanos.push_back(kMasCercanos);

    return decision(kMasCercanos, k, votacionPesada);
}

// usando el metodo de las potencias y ciertas hipotesis conseguimos el aval de mayor modulo y su avect asociado
pair<double, VectorXd> metPot(const MatrixXd &B, double epsilon){
    VectorXd v = VectorXd::Random(B.cols()); // esto inicializa como x0
    VectorXd temp;
    VectorXd prev = v;
    for(int i = 0; i <= numeroMaximoDeIteraciones; i++){
        temp = B * v;
        v = temp / (temp.norm());
        if((prev - v).norm() <= epsilon) break; // salgo del ciclo si el autovector actual es "similar" a el autovector de la iteracion anterior
        prev = v;
        if(i == numeroMaximoDeIteraciones) cout << "no conv..." << endl; // warning de convergencia
    }
    double lambda = (v.transpose() * (B * v))(0,0) / (v.squaredNorm());  // calculamos el autovalor
    return make_pair(lambda, v);
}

// modifica la matriz B, sacando el aval lambda
void deflacion(MatrixXd &B, double lambda, const VectorXd &v){
    B -= lambda * v * v.transpose();  // "sacamos" el autovalor lambda
}

// imprimimos la matriz de Eigen V en un csv nombre
void exportMat(MatrixXd &V, string nombre){
    fstream CreateFile("../csv/" + nombre, ios::out);
    if(nombre.substr(0,5) == "avect"){ // esto sirve para poner un primer renglon redundante en caso de imprimir los autovectores (sirve para reusar codigo en python)
        for(int j = 0; j < V.cols() - 1; j++) CreateFile << "pixel" << j << ",";
        CreateFile << "pixel" << (V.cols()-1) << endl;
    }
    for(int i = 0; i < V.rows(); i++){
        for(int j = 0; j < V.cols() - 1; j++) CreateFile << V(i,j) << ",";
        CreateFile << V(i, V.cols() - 1) << endl;  // esto lo hacemos fuera del ciclo para no agregar una coma al final
    }
}

// imprimimos la matriz de kNN K en un csv nombre
void exportKMasCercanos(const vector<vector<pair<int,double>>> &K, string nombre){
    fstream CreateFile("../csv/" + nombre, ios::out);
    if(nombre.substr(0,12) == "kMasCercanos"){ // esto sirve para poner un primer renglon redundante
        for(int j = 0; j < K[0].size() - 1; j++) CreateFile << "indice" << j << "," << "norma2" << j << ";";
        CreateFile << "indice" << (K[0].size()-1) << "," << "norma2" << (K[0].size()-1) << endl;
    }
    for(int i = 0; i < K.size(); i++){
        for(int j = 0; j < K[0].size() - 1; j++) CreateFile << K[i][j].first << "," << K[i][j].second << ";";
        CreateFile << K[i][K[0].size() - 1].first << "," << K[i][K[0].size() - 1].second << endl;  // esto lo hacemos fuera del ciclo para no agregar una coma al final
    }
}

// dado un vector de decisiones, produce un csv con el mismo formato que kaggle con las decisiones
void exportarResultados(const vector<int> &decisiones, string nombre){
    fstream CreateFile("../csv/" + nombre, ios::out);
    CreateFile << "ImageId,Label" << endl;
    for(int i = 0; i < decisiones.size(); i++) CreateFile << i+1 << "," << decisiones[i] << endl; // podemos pensar decisiones como un vetor de int y simplemente imprimir i+1 antes de la coma
}

int main(int argc, char **argv){
    // para simplificar conversiones, pasamos cada argumento a un string (queda un vector de argc strings)
    vector<string> argvst;
    for(int i = 0; i < argc; i++) argvst.push_back(argv[i]);

    // damos un valor predeterminado a los argumentos opcionales
    bool guardarKMasCercanosBool = false;
    bool guardarAvectsBool = true;
    bool leerKMasCercanos = false;
    bool leerAVect = false;
    string numFold = "0";  // esto podria ser un string vacio para parecerce mas a el codigo sin optimizar

    // parametros PCA, k, votPes, aplha, epsilon, csvEntr, csvTest, csvOut
    bool PCA = (stoi(argvst[1]) != 0);  // bool que dice si hacemos PCA o no
    int k = stoi(argvst[2]);  // k de kNN
    bool votacionPesada = (stoi(argvst[3]) != 0); // bool que dice si la votacion de kNN es pesada o no
    int alpha = stoi(argvst[4]); // cantidad de autovectores (componentes principales) de PCA
    double epsilonMP = stod(argvst[5]); // epsilon para metodo de la potencia
    string csvEntrenamiento = argvst[6]; // nombre del csv que usamos para entrenar
    string csvTest = argvst[7]; // nombre del csv sobre el cual queremos decidir
    string csvOut = argvst[8]; // nombre del csv donde imprimimos los resultados
    // argumentos opcionales
    if(argc > 9) guardarKMasCercanosBool = (stoi(argvst[9]) != 0);  // indica si guardamos la matriz de los kVecinos
    if(argc > 10) guardarAvectsBool = (stoi(argvst[10]) != 0);  // indica si guardamos la matriz de autovectores
    if(argc > 11) leerKMasCercanos = (stoi(argvst[11]) != 0);  // indica si leemos la matriz de kMasCercanos desde un csv (podemos ahorrarnos kNN y decidir muy rapido)
    if(argc > 12) leerAVect = (stoi(argvst[12]) != 0);  // indica si leemos la matriz de cambio de bases/autovectores desde un csv (podemos ahorrarnos metodo de la potencia y deflaciones y etc)
    if(argc > 13) numFold = argvst[13];  // indica el numero que se agrega los archivos (avect{numFold}.csv y kMasCercanos{numFold}./csv) esto sirve para guardar varios a la vez en paralelo

    vector<vector<int>> baseEntrenamiento; // como Eigen no tiene pushback, vamos a pasar los datos del csv a matices de C++ y luego traducirlas a Eigen
	vector<int> row;
	string line, word;
    bool copiarLinea = false; // este bool lo usamos para ignorar la primer fila del csv

    //copiamos los datos de entrenamiento a una matriz de C++
	fstream trainingFile ("../csv/" + csvEntrenamiento, ios::in);
	if(trainingFile.is_open()){
		while(getline(trainingFile, line)){
			if(copiarLinea){
            row.clear();
		    stringstream str(line);
		    while(getline(str, word, ',')) row.push_back(stoi(word));
		    baseEntrenamiento.push_back(row);
            } else copiarLinea = true;
		}
	} else cout<<"Could not open the file\n";

    vector<vector<int>> baseTest;
    copiarLinea = false;

    //copiamos los datos de test a una matriz de C++
    fstream testFile ("../csv/" + csvTest, ios::in);
	if(testFile.is_open()){
		while(getline(testFile, line)){
			if(copiarLinea){
            row.clear();
			stringstream str(line);
			while(getline(str, word, ',')) row.push_back(stoi(word));
			baseTest.push_back(row);
            } else copiarLinea = true;
		}
	} else cout<<"Could not open the file\n";

    int cantPixeles = baseTest[0].size();
    int trainSize = baseEntrenamiento.size();
    int testSize = baseTest.size();

    MatrixXd eigenTrain(trainSize, cantPixeles);
    MatrixXd eigenTest(testSize, cantPixeles);
    VectorXi indicesEntrenamiento(trainSize); // inicializamos las matrices de eigen con los tamanos correspondientes

    for(int i = 0; i < trainSize; i++){  // traducimos la matriz de train de C++ a Eigen (en este caso sacamos la primer columna y la guardamos como un vector aparte)
        indicesEntrenamiento(i) = baseEntrenamiento[i][0];
        for(int j = 1; j < cantPixeles + 1; j++) eigenTrain(i, j-1) = baseEntrenamiento[i][j];
    }

    for(int i = 0; i < testSize; i++) for(int j = 0; j < cantPixeles; j++) eigenTest(i, j) = baseTest[i][j]; // traducimos la matriz de test de C++ a Eigen

    vector<int> decisiones(testSize);
    vector<vector<pair<int,double>>> guardarKMasCercanos;

    //copiamos los datos de kMasCercanos a una matriz de tuplas de C++
    if(leerKMasCercanos){
        vector<pair<int,double>> row2;
        copiarLinea = false;
        fstream kMasCercanosFile ("../csv/kMasCercanos" + numFold + ".csv", ios::in);
        if(kMasCercanosFile.is_open()){
        	while(getline(kMasCercanosFile, line)){
        		if(copiarLinea){
                row2.clear();
        		stringstream str(line);
        		while(getline(str, word, ';')){
                    int i = word.find(',');
                    row2.push_back(make_pair(stoi(word.substr(0,i+1)),stod(word.substr(i+1))));
                    }
        		guardarKMasCercanos.push_back(row2);
                } else copiarLinea = true;
        	}
        } else cout<<"Could not open the file\n";
    }

    MatrixXd V(cantPixeles, alpha);
    if(PCA && leerAVect){
        //copiamos los avects a una matriz de C++
        vector<double> row3;
        vector<vector<double>> aVects;
        copiarLinea = false;
        fstream aVectFile ("../csv/avect" + numFold + ".csv", ios::in);
	    if(aVectFile.is_open()){
	    	while(getline(aVectFile, line)){
	    		if(copiarLinea){
                row3.clear();
	    		stringstream str(line);
	    		while(getline(str, word, ',')) row3.push_back(stod(word));
	    		aVects.push_back(row3);
                } else copiarLinea = true;
	    	}
	    } else cout<<"Could not open the file\n";

        // traducimos los avects a matriz de Eigen
        MatrixXd Vtemp(aVects.size(), aVects[0].size());
        for(int i = 0; i < Vtemp.rows(); i++) for(int j = 0; j < Vtemp.cols(); j++) Vtemp(i, j) = aVects[i][j];
        Vtemp.transposeInPlace();
        for(int i = 0; i < alpha; i++) V.col(i) = Vtemp.col(i);
    }

    if(PCA){
        auto startPCA = chrono::steady_clock::now();  // empezamos a tomar el tiempo
        if(!leerKMasCercanos){
            MatrixXd X = eigenTrain.rowwise() - eigenTrain.colwise().mean();  // centramos (sacamos la media de cada columna a cada columna) train en una linea (gracias Eigen)
            if(!leerAVect){
                MatrixXd Mx = (X.transpose() * X) / (X.rows() - 1);  // calculamos matriz de covarianza con una linea mas (gracias Eigen)
                pair<double, VectorXd> tempTrain;  // aca guardamos temporalmente el autovalor y autovector
                for(int i = 0; i < alpha; i++){
                    tempTrain = metPot(Mx, epsilonMP);  // conseguimos el aval y avect
                    deflacion(Mx, tempTrain.first, tempTrain.second);  // sacamos el aval de Mx (por referencia)
                    V.col(i) = tempTrain.second;  // ponemos el avect como columna i-esima de V
                }
            }
            MatrixXd Y = eigenTest.rowwise() - eigenTrain.colwise().mean();  // centramos test (sacamos la media de train de cada columna a cada columna)
            MatrixXd PCATrain = X * V / sqrt(eigenTrain.rows()-1);  // pasamos train al nuevo mundo
            MatrixXd PCATest = Y * V / sqrt(eigenTrain.rows()-1);  // pasamos test al nuevo mundo
            for(int i = 0; i < PCATest.rows(); i++) decisiones[i] = kNN(PCATest.row(i), k, votacionPesada, PCATrain, indicesEntrenamiento, guardarKMasCercanos, guardarKMasCercanosBool); // decidimos con kNN el label de la i-esima imagen de test
        } else for(int i = 0; i < eigenTest.rows(); i++) decisiones[i] = decision(guardarKMasCercanos[i], k, votacionPesada);
        auto endPCA = chrono::steady_clock::now();  // dejamos de medir el tiempo
        cout << "PCA elapsed time in seconds: "
        << chrono::duration_cast<chrono::seconds>(endPCA - startPCA).count()
        << " s" << endl;  // imprimimos el tiempo de ejecucion de PCA
        MatrixXd Vt = V.transpose();  // transponemos para que quede mas comodo al imprimir el autovector como imagen en python
        if(guardarAvectsBool) exportMat(Vt,"avect" + numFold + ".csv");  // guardamos los alpha autovectores de train calculados en esta ejecucion
        if(guardarKMasCercanosBool) exportKMasCercanos(guardarKMasCercanos,"kMasCercanos" + numFold + ".csv");  // guardamos los kVecinosMasCercanos entre test y train calculados en esta ejecucion
    } else {
        auto startkNN = chrono::steady_clock::now();  // empezamos a tomar el tiempo
        if(leerKMasCercanos) for(int i = 0; i < eigenTest.rows(); i++) decisiones[i] = decision(guardarKMasCercanos[i], k, votacionPesada);
        else for(int i = 0; i < eigenTest.rows(); i++) decisiones[i] = kNN(eigenTest.row(i), k, votacionPesada, eigenTrain, indicesEntrenamiento, guardarKMasCercanos, guardarKMasCercanosBool); // decidimos con kNN el label de la i-esima imagen de test
        auto endkNN = chrono::steady_clock::now();  // dejamos de medir el tiempo
        cout << "kNN elapsed time in seconds: "
        << chrono::duration_cast<chrono::seconds>(endkNN - startkNN).count()
        << " s" << endl;  // imprimimos el tiempo de ejecucion de kNN
        if(guardarKMasCercanosBool) exportKMasCercanos(guardarKMasCercanos,"kMasCercanos" + numFold + ".csv");  // guardamos los kVecinosMasCercanos entre test y train calculados en esta ejecucion
    }
    exportarResultados(decisiones, csvOut);
}