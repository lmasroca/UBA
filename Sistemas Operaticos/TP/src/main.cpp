#include <iostream>
#include <thread>
#include <sys/unistd.h>
#include <vector>
#include <mutex>
#include "gameMaster.h"
#include "equipo.h"
#include "definiciones.h"
#include "config.h"
#include <assert.h>

using namespace std;

const estrategia strat = RR;
const int quantum = 10;

color jugar(string configName, int quantum, estrategia strat){
	Config config = *(new Config(configName));

	// ahora belcebu recibe tambien la strat, sirve para resetear los semaforos dependiendo la estrategia
    gameMaster belcebu = gameMaster(config, strat);


	// Creo equipos (lanza procesos)
	Equipo rojo(&belcebu, ROJO, strat, config.cantidad_jugadores, quantum, config.pos_rojo);
	Equipo azul(&belcebu, AZUL, strat, config.cantidad_jugadores, quantum, config.pos_azul);


    rojo.comenzar();
    azul.comenzar();
	rojo.terminar();
	azul.terminar();

	cout << "El ganador es: " << belcebu.ganador << endl;
    return belcebu.ganador;
}


int main(){
	estrategia strat = RR;
	int quantum = 10;
	string configName = "config_parameters.csv";

	cout <<"test 1: strat RR, quantum 10" << endl;
	assert(jugar("config_parameters.csv", quantum, strat) == AZUL);

	strat = SHORTEST;

	cout <<"test 2: strat SHORTEST, quantum 10" << endl;
	assert(jugar("config_parameters.csv", quantum, strat) == AZUL);

	strat = USTEDES;

	cout <<"test 3: strat USTEDES, quantum 10" << endl;
	assert(jugar("config_parameters.csv", quantum, strat) == AZUL);

	strat = SECUENCIAL;

	cout <<"test 4: strat SECUENCIAL, quantum 10" << endl;
	assert(jugar("config_parameters.csv", quantum, strat) == AZUL);

	strat = RR;
	quantum = 3;

	cout <<"test 5: strat RR, quantum 3" << endl;
	assert(jugar("config_parameters.csv", quantum, strat) == EMPATE);

	strat = USTEDES;
	quantum = 200;
	cout <<"test 6: strat USTEDES, quantum 200" << endl;
	assert(jugar("config_parameters.csv", quantum, strat) == ROJO);

	return 0;
}

