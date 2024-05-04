#include <vector>
#include <iostream>
using namespace std;
using Ecuacion = pair<pair<int,int>, int>;

bool fishburn(vector<int> &d, vector<Ecuacion> &ecuaciones, vector<int> &res){
    for(int i = 0; i < res.size(); i++) res[i] = d.size()-1;
    bool cambio;
    do {
        cambio = false;
        for(int i = 0; i < ecuaciones.size(); i++){
            int a = ecuaciones[i].first.first;
            int b = ecuaciones[i].first.second;
            int c = ecuaciones[i].second;
            if(d[res[b]] + c < d[res[a]]){
                for (int j = res[a] - 1; j >= 0; j--){
                    if(d[res[b]] + c >= d[j]){
                        res[a] = j;
                        cambio = true;
                        break;
                    }
                }
            }
        }
    } while(cambio);
    for(int i = 0; i < ecuaciones.size(); i++){
        int a = ecuaciones[i].first.first;
        int b = ecuaciones[i].first.second;
        int c = ecuaciones[i].second;
        if (d[res[a]] - d[res[b]] > c) return false;
    }
    return true;
}

int main(){
    long long int t, k, n, m, a, b, c, dj;
    cin >> t;
    for(int i = 0; i < t; i++){
        cin >> k >> n >> m;
        vector<int> d(m, 0);
        vector<Ecuacion> ecuaciones(k);
        vector<int> res(n, 0);
        for (int j = 0; j < k; j++) {
            cin >> a >> b >> c;
            // ecuacion xa - xb <= c
            ecuaciones[j] = make_pair(make_pair(a - 1, b - 1), c); // restamos uno porque indexan desde uno
        }
        for(int j = 0; j < m; j++){
            cin >> dj;
            // elementos de D
            d[j] = dj;
        }
        bool satisfacible = fishburn(d, ecuaciones, res);
        if (satisfacible) {
            for (int j = 0; j < res.size(); j++) cout << res[j] + 1 << " "; // sumamos uno pq d se indexa desde uno
            cout << endl;
        } else cout << "insatisfactible" << endl;
    }
    return 0;
}