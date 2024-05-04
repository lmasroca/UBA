#include <vector>
#include <iostream>
using namespace std;
using Grafo = vector<vector<int>>;

//RECURSIVO CON TIMER Y COLORES
void dfs_timer(const vector<vector<int>> &ady, const int source, int &current_time, vector<int> &time_in, vector<int> &color){
    color[source] = 1;
    time_in[source] = current_time++;
    for (int u : ady[source]){
        if (color[u] == 0){
            dfs_timer(ady, u, current_time, time_in, color);
        }
    }
    color[source] = 2;
}

//RECURSIVO CON PARENT
void dfs_parent(Grafo& ady, int source, vector<int>& parents){
    for (int u : ady[source]){
        if (parents[u] == -1){
            parents[u] = source;
            dfs_parent(ady, u, parents);
        }
    }
}

void dfs_ancestro(Grafo& ady, int source, int abuelo, vector<int>& ancestro){
    for (int u : ady[source]){
        if (ancestro[u] == -1){
            ancestro[u] = abuelo;
            dfs_ancestro(ady, u, abuelo, ancestro);
        }
    }
}

// DFS y dp de la clase practica de este cuatrimestre (2C2022) https://campus.exactas.uba.ar/mod/resource/view.php?id=286518
int dp(const Grafo& ady, int source, const vector<int> &time_in, const vector<int> &parents, vector<bool> &puente_con_parent){
    int cantidad = 0;
    for (int u : ady[source]){
        if (parents[u] == source){
            cantidad += dp(ady, u, time_in, parents, puente_con_parent);
        } else {
            if (time_in[source] > time_in[u] && parents[source] != u){ //(source,u) es backedge que sube
                cantidad++;
            }
            if (time_in[source] < time_in[u]){ //(source,u) es backedge que viene de abajo
                cantidad--;
            }
        }
    }
    if (cantidad == 0 && parents[source] != source){
        puente_con_parent[source] = true;
    }
    return cantidad;
}

vector<int> precomputo(Grafo g){
    int n = g.size();
    vector<int> parents(n, -1);
    vector<int> time_in(n, 0);
    vector<int> color(n, 0);
    vector<bool> puente_con_parent(n + 1, false);
    int current_time = 0;
    for (int i = 0; i < n; i++){
        if(parents[i] == -1){
            parents[i] = i;
            dfs_parent(g, i, parents);
            dfs_timer(g, i, current_time, time_in, color);
            dp(g, i, time_in, parents, puente_con_parent);
        }
    }
    vector<int> respuesta(n, -1);
    Grafo gp(n);
    for (int i = 0; i < n; i++){
        if (puente_con_parent[i]){
            gp[i].push_back(parents[i]);
            gp[parents[i]].push_back(i);
        }
    }
    for (int i = 0; i<n; i++){
        if (respuesta[i] == -1){
            respuesta[i] = i;
            dfs_ancestro(gp, i, i, respuesta);
        }
    }
    return respuesta;
}

char responderQuery(vector<int> &ccp, int s, int t){
    return (ccp[s] == ccp[t]) ? 'Y' : 'N';
}

int main(){
    int r, c, q, v, w, s, t;
    while(1){
        cin >> r >> c >> q;
        if(r == 0 && c == 0 && q == 0) break;
        Grafo g(r + 1);
        for (int i = 0; i < c; i++){
            cin >> v >> w;
            // agregar arista v-w al grafo
            g[v].push_back(w);
            g[w].push_back(v);
        }
        // precomputo
        vector<int> ccp = precomputo(g);
        for (int i = 0; i < q; i++){
            cin >> s >> t;
            // calcular en O(1) para cada query
            cout << responderQuery(ccp, s, t) << endl;
        }
        cout << "-" << endl;
    }
    return 0;
}