#include <iostream>

#include <vector>

using namespace std;

int tam;
int pc;
int sc;
int tc;

bool cas_invalida(int m, int n, pair<int, int> c){
    return c.first < 0 || c.first >= m || c.second < 0 || c.second >= n;
}

bool deje_bloques_bordes(pair<int, int> c, vector<vector<bool>> &recorridas){
    int m = recorridas.size();
    int n = recorridas[0].size();
    if(c.second == 0){ //borde izquierdo
        for(int i = 0; i < c.first; i++){
            if (!recorridas[i][0]){
                return true;
            }
        }
    } else if(c.first == m - 1){ //borde arriba
        for(int i = 0; i < c.second; i++){
            if (!recorridas[m - 1][i]){
                return true;
            }
        }
    } else if(c.second == n - 1){ //borde derecha
        for(int i = c.first + 1; i < m; i++){
            if (!recorridas[i][n - 1]){
                return true;
            }
        }
    } else if (c.first == 0){ //borde abajo
        for(int i = c.second + 1; i < n; i++){
            if (!recorridas[0][i]){
               return true;
            }
        }
    }
    return false;
}

bool llego_al_check_point(int k, pair<int, int> c, vector<vector<bool>> &recorridas, vector<pair<int, int>> &check_points ){
    if(k < pc){
        return abs(check_points[0].first - c.first) + abs(check_points[0].second - c.second) <= pc - k;
    } else if(k > pc && k < sc){
        return abs(check_points[1].first - c.first) + abs(check_points[1].second - c.second) <= sc - k;
    } else if (k > sc && k < tc){
        return abs(check_points[2].first - c.first) + abs(check_points[2].second - c.second) <= tc - k;
    }
    return true;
}

bool islas(int n, int m, pair<int, int> c, vector<vector<bool>> &recorridas){
    if (c.first - 1 < 0 && c.first + 1 >= m && c.second - 1 < 0 && c.second + 1 >= n){
        if (recorridas[c.first - 1][c.second] && recorridas[c.first + 1][c.second]){
            return !recorridas[c.first][c.second + 1] && !recorridas[c.first][c.second - 1];
        }
        if (recorridas[c.first][c.second - 1] && recorridas[c.first][c.second + 1]){
            return !recorridas[c.first - 1][c.second] && !recorridas[c.first + 1][c.second];
        }
    }
    return false;
}

int caminos (int k, int m, int n, pair<int, int> c, vector<pair<int, int>> &check_points, vector<vector<bool>> &recorridas){
    int res = 0;
    pair<int, int> cfin = make_pair(0, 1);
    if (k == tam && c == cfin){
        recorridas[c.first][c.second] = false;
        return 1;
    } else if ((k == pc && c != check_points[0]) || (k != pc && c == check_points[0])){
        recorridas[c.first][c.second] = false;
        return 0;
    } else if ((k == sc && c != check_points[1]) || (k != sc && c == check_points[1])){
        recorridas[c.first][c.second] = false;
        return 0;
    } else if ((k == tc && c != check_points[2]) || (k != tc && c == check_points[2])){
        recorridas[c.first][c.second] = false;
        return 0;
    } else if (c == cfin){
        recorridas[c.first][c.second] = false;
        return 0;
    } else if (cas_invalida(m, n, c) ||  recorridas[c.first][c.second]){
        return 0;
    } else if (!llego_al_check_point(k, c, recorridas,check_points) || deje_bloques_bordes(c, recorridas)) {
         recorridas[c.first][c.second] = false;
         return 0;
    } else if (islas(n, m, c, recorridas)){
        recorridas[c.first][c.second] = false;
        return 0;
    }
    recorridas[c.first][c.second] = true;
    pair<int, int> c1 = make_pair(c.first, c.second + 1);
    pair<int, int> c2 = make_pair(c.first, c.second - 1);
    pair<int, int> c3 = make_pair(c.first + 1, c.second);
    pair<int, int> c4 = make_pair(c.first - 1, c.second);
    pair<int, int> ccc;
    if (!cas_invalida(m, n, c1)){
        ccc = c1;
        if (!recorridas[ccc.first][ccc.second]){
            res = res + caminos(k + 1, m, n, ccc, check_points, recorridas);
        }
    }
    if (!cas_invalida(m, n, c2)){
        ccc = c2;
        if (!recorridas[ccc.first][ccc.second]){
            res = res + caminos(k + 1, m, n, ccc, check_points, recorridas);
        }
    }
    if (!cas_invalida(m, n, c3)){
        ccc = c3;
        if (!recorridas[ccc.first][ccc.second]){
            res = res + caminos(k + 1, m, n, ccc, check_points, recorridas);
        }
    }
    if (!cas_invalida(m, n, c4)){
        ccc = c4;
        if (!recorridas[ccc.first][ccc.second]){
            res = res + caminos(k + 1, m, n, ccc, check_points, recorridas);
        }
    }
    recorridas[c.first][c.second] = false;
    return res;
}

int main (){
    int m;
    int n;
    int r;
    int c;
    int caso = 1;
    vector<pair<int, int>> check_points(3);

    while(1){
        cin >> m;
        cin >> n;
        if(m == 0 && n == 0) break;
        for(int i = 0; i < 3; i++){
            cin >> r ;
            cin >> c ;
            check_points[i]= (make_pair(r, c)) ;
        }
        vector<vector<bool>> recorridas(m, vector<bool>(n, false));
        tam = m * n;
        pc = tam / 4;
        sc = tam / 2;
        tc = tam * 0.75;
        int res = caminos(1, m, n, make_pair(0, 0), check_points, recorridas);
        cout << "Case " << caso << ": " << res << endl;
        caso++;
    }
    return 0;
}