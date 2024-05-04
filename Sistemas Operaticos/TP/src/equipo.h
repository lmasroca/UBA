#ifndef EQUIPO_H
#define EQUIPO_H

#include <semaphore.h>
#include <vector>
#include <thread>
#include <atomic>
#include "definiciones.h"
#include "gameMaster.h"

using namespace std;

class Equipo {
	private:

		// Atributos Privados 
		gameMaster *belcebu; 
		color contrario, equipo, bandera_contraria;
		estrategia strat;
		int cant_jugadores, quantum, quantum_restante;
		vector<thread> jugadores;
		int cant_jugadores_que_ya_jugaron = 0;
		vector<coordenadas> posiciones;
		coordenadas pos_bandera_contraria;
		// nuevos:
		atomic<bool> encontro_bandera;
		atomic<int> movidos;
		vector<sem_t>* orden;
		int mas_cercano;
		sem_t busqueda;


		// Métodos privados 
		direccion apuntar_a(coordenadas pos2, coordenadas pos1);
		void jugador(int nro_jugador);
		void buscar_bandera_contraria(int nro_jugador); // cambiamos a void
		//nuevo
		int mover(int nro_jugador); // siempre movemos el jugador hacia la bandera contraria

	public:
		Equipo(gameMaster *belcebu, color equipo, 
				estrategia strat, int cant_jugadores, int quantum, vector<coordenadas> posiciones);
		void comenzar();
		void terminar();
		// crear jugadores

};

#endif // EQUIPO_H
