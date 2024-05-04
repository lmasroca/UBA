#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
int INF = numeric_limits<int>::max() / 2; // divido por 2 para evitar overflow, harcodear 100000000 para uva
using Costo = unsigned long int;
using Arista = pair<size_t, size_t>;
using Grafo = vector<pair<Costo, Arista>>;

// //Estructura Union-Find.
// //Proporciona dos funciones: Unite y Find
struct UF{
    //vectores internos
    //  p: Vector con los parents, s: Vector con tamaños
    vector<int> p, s;
    //Constructor
    UF(int n): p(n, -1), s(n, 1) {}

    //unite: Hace la unión y devuelve el tamaño del resultante
    int unite(int v, int w) {
        v = find(v); w = find(w);
        if(v == w) return s[v];
        if(s[v] < s[w]) swap(v, w);
        p[w] = v; //El árbol de mayor tamaño queda como padre
        return s[v] += s[w];
    }
    //find: Devuelve el parent
    int find(int v) {
        return p[v] == -1 ? v : p[v] = find(p[v]); //Recursión para path compression
    }
};

// UF y kruskal de la practica de este cuatrimestre (2C2022) https://campus.exactas.uba.ar/pluginfile.php/420842/mod_resource/content/2/kruskal.cpp
// Kruskal modificado
int kruskal(Grafo &g, vector<vector<pair<int, int>>> &maxMin, int n){
    sort(g.begin(), g.end(), greater<pair<Costo, Arista>>());
    UF componentes(n + 1);
    int res = 0;
    for(int i = 0; i < g.size(); i++){
        pair<Costo, Arista> arista = g[i];
        int v = arista.second.first;
        int w = arista.second.second;
        if(componentes.find(v) != componentes.find(w)){
            int vf = componentes.find(v);
            int wf = componentes.find(w);
            int size = componentes.unite(v, w);
            int u = componentes.find(v);
            int maximo = -1;
            for(int j = 0; j < maxMin[u].size(); j++){
                int jf = componentes.find(j);
                if (jf != vf & jf != wf){
                    maxMin[u][jf].first = max(maxMin[vf][jf].first, maxMin[wf][jf].first);
                    maxMin[jf][u].first = maxMin[u][jf].first;
                    if (maximo < maxMin[u][jf].first) maximo = maxMin[u][jf].first;
                }
            }
            for(int j = 0; j < maxMin[vf].size(); j++){
                int jf = componentes.find(j);
                if (jf != vf & jf != wf){
                    maxMin[u][jf].second = min(maxMin[vf][jf].second, maxMin[wf][jf].second);
                    maxMin[jf][u].second = maxMin[u][jf].second;
                }
            }
            maxMin[u][u].first = maximo;
            maxMin[u][u].second = min(maxMin[vf][vf].second, min(maxMin[wf][wf].second, maxMin[vf][wf].second));
            if(maxMin[u][u].first < maxMin[u][u].second) res += size;
        }
    }
    return res;
}

int main(){
    long long int t, n, m, v, w, k;
    cin >> t;
    for(int i = 0; i < t; i++){
        cin >> n >> m;
        Grafo g(m);
        vector<vector<pair<int, int>>> maxMin(n + 1);
        for(int j = 0; j < n + 1; j++) maxMin[j] = vector<pair<int, int>>(n+1, make_pair(-1, INF));
        for (int j = 0; j < m; j++){
            cin >> v >> w >> k;
            // agregar arista v-w de peso k al grafo
            g[j] = make_pair(k, make_pair(v, w));
            maxMin[v][w] = make_pair(k, k);
            maxMin[w][v] = make_pair(k, k);
        }
        cout << kruskal(g, maxMin, n) << endl;
    }
    return 0;
}