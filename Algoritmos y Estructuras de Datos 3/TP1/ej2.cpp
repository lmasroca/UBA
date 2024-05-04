#include <vector>
#include <string>
#include <math.h>
#include <iostream>
#include <algorithm>
using namespace std;
using Segmento = pair<double, double>;

int cantMinAspersores(double l, const vector<Segmento> &segAP){
    int maxIndex = 0;
    int cantAspersores = 0;
    double segmentoLibreIzq = 0;
    while(segmentoLibreIzq < l && maxIndex < segAP.size()){
        double area = 0;
        double maxArea = 0;
        int k = maxIndex;
        while(k < segAP.size() && segAP[k].first <= segmentoLibreIzq){
            if (segAP[k].second > segmentoLibreIzq){
                area = min(segAP[k].second,l) - max(max(segmentoLibreIzq, (double) 0), segAP[k].first);
                if (maxArea <= area) {maxArea = area; maxIndex = k;}
            }
            k++;
        }
        segmentoLibreIzq = segAP[maxIndex].second;
        if (maxArea <= 0) return -1;
        cantAspersores++;
        int maxIndex = k;
    }
    if (segmentoLibreIzq < l) return -1;
    return cantAspersores;
}

int main(){
    long long int n, x, r;
    double l, w;
    while(cin >> n >> l >> w){
        w /= 2;
        vector<Segmento> segAP;
        for(int i = 0; i < n; i++){
            cin >> x >> r;
            if (r <= w) continue;
            double rr = sqrt(r * r - w * w);
            if (x - rr > l || x + rr < 0) continue;
            segAP.push_back(make_pair(x - rr, x + rr));
        }
        sort(segAP.begin(), segAP.end());
        cout << cantMinAspersores(l, segAP) << endl;
    }
    return 0;
}