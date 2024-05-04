#include <vector>
#include <string>
#include <math.h>
#include <iostream>
#include <algorithm>
using namespace std;
using Segmento = pair<double, double>;
using Aspersor = pair<Segmento, long long int>;
double& izq(Segmento &s) { return s.first; }
double& der(Segmento &s) { return s.second; }
Segmento& seg(Aspersor &a) { return a.first; }
double& izq(Aspersor &a) { return a.first.first; }
double& der(Aspersor &a) { return a.first.second; }
long long int& costo(Aspersor &a) { return a.second; }
long long int INF = std::numeric_limits<long long int>::max() / 2; // divido por 2 para evitar overflow

int argmax(int i, int j, vector<Aspersor> & aspersores){if(der(aspersores[i]) >= der(aspersores[j])) {return i;} else {return j;}}

long long int costoMin(int i, int j, double l, long long int n, vector<Aspersor> &aspersores, vector<vector<long long int>> &memo){
    if(memo[i][j] == NULL){
        if (i == n + 1 && der(aspersores[j]) >= l) memo[i][j] = 0; // no puedo prender mas y ya cubri todo
        else if ((i == n + 1 && der(aspersores[j]) < l) || der(aspersores[j]) < izq(aspersores[i])) memo[i][j] = INF; // no puedo prender mas y no cubri todo o me saltee un segmento
        else memo[i][j] = min(costoMin(i + 1, j, l, n, aspersores, memo), costo(aspersores[i]) + costoMin(i + 1, argmax(i, j, aspersores), l, n, aspersores, memo)); // prender o no prender
    }
    return memo[i][j];
}

int main(){
    long long int n, x, r, c;
    double l, w;
    while(cin >> n >> l >> w){
        w /= 2;
        vector<Aspersor> aspersores;
        for (int i = 0; i < n; i++){
            cin >> x >> r >> c;
            if (r <= w) continue;
            double rr = sqrt(r * r - w * w);
            if (x-rr > l || x+rr < 0) continue;
            aspersores.push_back({{x - rr, x + rr}, c});
        }
        aspersores.push_back({{-INF, 0}, 0});
        sort(aspersores.begin(), aspersores.end());
        int size = aspersores.size();
        vector<vector<long long int>> memo(size + 2, vector<long long int>(size + 2, NULL));
        long long int res = costoMin(0, 0, l, size, aspersores, memo);
        if (res == INF) cout << -1 << endl;
        else cout << res << endl;
    }
    return 0;
}