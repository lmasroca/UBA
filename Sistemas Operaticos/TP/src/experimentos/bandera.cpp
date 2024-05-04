#include <assert.h>
#include <time.h>
#include <iostream>
#include <chrono>
#include <atomic>
#include <vector>
#include <thread>

using namespace std;

typedef pair<int, int> coordenadas;
enum color {VACIO=0,BANDERA_AZUL};
int X, Y, cant_jugadores;
atomic<bool> encontro_bandera;
coordenadas bandera_contraria, pos_bandera_contraria;
bool paralelo;
vector<vector<color>> mapa;
color en_posicion(coordenadas coord) {return mapa[coord.first][coord.second];}

void buscar_bandera_contraria(int nro_jugador) {
	int tam = X * Y;
	assert((tam%cant_jugadores) == 0);
	int cuantos = tam / cant_jugadores; // tamaño del tablero divisible por la cantidad de jugadores
	for(int i = nro_jugador * cuantos; i < (nro_jugador + 1) * cuantos; i++){
		if(encontro_bandera.load()) break;
		coordenadas pos = make_pair(i / Y, i % Y);
		if(en_posicion(pos) == BANDERA_AZUL){
			pos_bandera_contraria = pos;
			encontro_bandera.store(true);
		}
	}
}

int main(int argc, char **argv) {
    vector<string> argvst;
    for(int i = 0; i < argc; i++) argvst.push_back(argv[i]);

    paralelo = (stoi(argvst[1]) != 0);
    X = stoi(argvst[2]);
    Y = stoi(argvst[3]);
    cant_jugadores = stoi(argvst[4]);
    bandera_contraria.first = stoi(argvst[5]);
    bandera_contraria.second = stoi(argvst[6]);
    vector<vector<color>> mapaNuevo(X, vector<color>(Y, VACIO));
    mapa = mapaNuevo;
    mapa[bandera_contraria.first][bandera_contraria.second] = BANDERA_AZUL;
    vector<thread> jugadores;

    auto start = chrono::steady_clock::now();
    if (paralelo) {
        for(int i = 0; i < cant_jugadores; i++) jugadores.emplace_back(thread(&buscar_bandera_contraria, i)); 
        for(auto &t:jugadores) t.join();
    } else {
        int i = 0;
        while(i < X * Y && en_posicion((coordenadas){i / Y, i % Y}) != BANDERA_AZUL) {i++;}
        pos_bandera_contraria = (coordenadas){i / Y, i % Y};
    }
    auto end = chrono::steady_clock::now();
    cout << chrono::duration_cast<chrono::nanoseconds>(end - start).count() << " ns" << endl;

    assert(pos_bandera_contraria == bandera_contraria);
    return 0;
}