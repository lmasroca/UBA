#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <cmath>
using namespace std;
using Edge = pair<int, int>;
using Digrafo = vector<vector<Edge>>;
using AristaP = pair<pair<int, int>, int>;
using Multigrafo = vector<AristaP>;
int INF = numeric_limits<int>::max() / 2; // divido por 2 para evitar overflow, harcodear 100000000 para uva

// Dijkstra min heap
// Fuente: practica primer cuatrimestre 2022
// https://campus.exactas.uba.ar/pluginfile.php/383892/mod_resource/content/1/minimumPath.cpp
void dijkstra(vector<vector<Edge>>& G, int s, vector<int>& d, vector<int>& p) {
    int n = G.size();
    d.assign(n, INF);
    p.assign(n, -1);
    d[s] = 0;
    priority_queue<Edge, vector<Edge>, greater<Edge>> q;
    q.push({0, s});
    while (!q.empty()) {
        int v = q.top().second;
        int d_v = q.top().first;
        q.pop();
        if (d_v != d[v]) continue;
        for (Edge edge : G[v]) {
            int to = edge.first;
            int len = edge.second;
            if (d[v] + len < d[to]) {
                d[to] = d[v] + len;
                p[to] = v;
                q.push({d[to], to});
            }
        }
    }
}

int main(){
    int t, c, p, q, j, r, ck, pk, res;
    cin >> t;
    for(int i; i < t; i++){
        cin >> c >> p;
        cin >> q;
        Multigrafo mgrafo;
        Digrafo grafo(p + 1);
        vector<int> vuelve(p, INF);
        vector<int> dist(p + 1, INF);
        vector<int> parent(p + 1, INF);
        dist[0] = 0;
        for (int k = 0; k < q; k++){
            cin >> j; // feligreses a los cuales les puede pasar la caja el portero (costo 0)
            mgrafo.push_back({{0, j}, 0});
        }
        for(int k = 1; k <= p; k++){
            cin >> r;
            for (int h = 0; h < r; h++) {
                cin >> ck >> pk; // regla k-esima del i-esimo feligres, define aristas y pesos en el grafo
                if (pk == 0) vuelve[k-1] = min(ck, vuelve[k-1]); // lo guardamos aparte si vuelve al portero
                mgrafo.push_back({{k, pk}, ck});
            }
        }
        // sort multigrafo y pasarlo a digrafo
        sort(mgrafo.begin(), mgrafo.end());
        for(int j = 0; j < mgrafo.size(); j++){
            int v = mgrafo[j].first.first;
            int w = mgrafo[j].first.second;
            int costo = mgrafo[j].second;
            if (grafo[v].size() == 0 || grafo[v][grafo[v].size()-1].first != w) grafo[v].push_back(make_pair(w, costo));
        }
        dijkstra(grafo, 0, dist, parent);
        res = INF;
        for(int k = 0; k < p; k++) if (vuelve[k] < INF & (res > dist[k + 1] + vuelve[k])) res = dist[k + 1] + vuelve[k];
        if (res == INF || c == 1) res = 0;
        else res = ceil((double) (c - 1) / (double) (res - 1)) - 1;
        cout << res << endl;
    }
    return 0;
}