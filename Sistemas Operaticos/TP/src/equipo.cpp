#include "equipo.h"
#include <assert.h>     /* assert */


direccion Equipo::apuntar_a(coordenadas pos1, coordenadas pos2) {
	if (pos2.second > pos1.second) return ABAJO;
	if (pos2.second < pos1.second) return ARRIBA;
	if (pos2.first > pos1.first) return DERECHA;
	else return IZQUIERDA;
}

int Equipo::mover(int nro_jugador){return belcebu->mover_jugador(apuntar_a(belcebu->getPosJugador(equipo, nro_jugador), pos_bandera_contraria), nro_jugador);}

void Equipo::jugador(int nro_jugador) {
	buscar_bandera_contraria(nro_jugador);
	while(!encontro_bandera.load());
	if (this->strat == SHORTEST || this->strat == USTEDES) {
		if (nro_jugador == 0){
			for (int i = 0; i < cant_jugadores; i++) if (this->belcebu->distancia(posiciones[i], pos_bandera_contraria) < this->belcebu->distancia(posiciones[mas_cercano], pos_bandera_contraria)) mas_cercano = i;
			for (int i = 0; i < cant_jugadores; i++) sem_post(&this->busqueda);
		}
		sem_wait(&this->busqueda);
	}
	belcebu->wait(equipo, nro_jugador);
	while(!this->belcebu->termino_juego()) { // Chequear que no haya una race condition en gameMaster
		switch(this->strat) {
			//SECUENCIAL,RR,SHORTEST,USTEDES
			case(SECUENCIAL):
				mover(nro_jugador);
				if (atomic_fetch_add(&this->movidos, 1) == this->cant_jugadores) {
					atomic_store(&this->movidos, 1);
					belcebu->termino_ronda(equipo);
				}
				break;

			case(RR):
				if (quantum_restante == 0) {
					quantum_restante = quantum;
					belcebu->termino_ronda(equipo);
				} else {
					quantum_restante--;
					mover(nro_jugador);
					belcebu->signal(equipo, (nro_jugador+1)%cant_jugadores);
				}
				break;

			case(SHORTEST):
				if (nro_jugador == mas_cercano) {
					mover(nro_jugador);
					belcebu->termino_ronda(equipo);
				}
				break;

			case(USTEDES):
				// SHORTEST mejorado
				if (nro_jugador == mas_cercano) {
					for(int i = 0; i < quantum; i++) mover(nro_jugador);
					belcebu->termino_ronda(equipo);
				}
				break;
			default:
				break;
		}	
		// Termino ronda ? Recordar llamar a belcebu...
		// OJO. Esto lo termina un jugador... 
		if (!this->belcebu->termino_juego()) belcebu->wait(equipo, nro_jugador);
	}

}

Equipo::Equipo(gameMaster *belcebu, color equipo, 
		estrategia strat, int cant_jugadores, int quantum, vector<coordenadas> posiciones) {
	this->belcebu = belcebu;
	this->equipo = equipo;
	this->contrario = (equipo == ROJO)? AZUL: ROJO;
	this->bandera_contraria = (equipo==ROJO)? BANDERA_AZUL: BANDERA_ROJA;
	this->strat = strat;
	this->quantum = quantum;
	this->quantum_restante = quantum;
	this->cant_jugadores = cant_jugadores;
	this->posiciones = posiciones;
	// nuevos:
	this->encontro_bandera = false;
	this->movidos = 1;
	if (equipo == ROJO) this->orden = &belcebu->orden_rojo;
	else this->orden = &belcebu->orden_azul;
	this->mas_cercano = 0;
	sem_init(&this->busqueda, 0, 0);
}

void Equipo::comenzar() {
	// Arranco cuando me toque el turno 
	// TODO: Quien empieza ? 
	//
	// ...
	//

	// Creamos los jugadores
	for(int i=0; i < cant_jugadores; i++) {
		jugadores.emplace_back(thread(&Equipo::jugador, this, i)); 
	}
}

void Equipo::terminar() {
	for(auto &t:jugadores){
		t.join();
	}
}

void Equipo::buscar_bandera_contraria(int nro_jugador) {
	int tam = this->belcebu->getTamx() * this->belcebu->getTamy();
	assert((tam%cant_jugadores) == 0);
	int cuantos = tam / cant_jugadores; // tamaño del tablero divisible por la cantidad de jugadores
	for(int i = nro_jugador * cuantos; i < (nro_jugador + 1) * cuantos; i++){
		if(encontro_bandera.load()) break;
		coordenadas pos = make_pair(i / this->belcebu->getTamy(), i % this->belcebu->getTamy());
		if(this->belcebu->en_posicion(pos) == bandera_contraria){
			pos_bandera_contraria = pos;
			encontro_bandera.store(true);
		}
	}
}
