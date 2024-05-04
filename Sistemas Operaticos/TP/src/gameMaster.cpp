#include <sys/unistd.h>
#include <assert.h>     /* assert */
#include "gameMaster.h"

bool gameMaster::es_posicion_valida(coordenadas pos) {
	return (pos.first > 0) && (pos.first < x) && (pos.second > 0) && (pos.second < y);
}

bool gameMaster::es_color_libre(color color_tablero){
    return color_tablero == VACIO || color_tablero == INDEFINIDO;
}

color gameMaster::en_posicion(coordenadas coord) {
	return tablero[coord.first][coord.second];
}

int gameMaster::getTamx() {
	return x;
}

int gameMaster::getTamy() {
	return y;
}

int gameMaster::distancia(coordenadas c1, coordenadas c2) {
    return abs(c1.first-c2.first)+abs(c1.second-c2.second);
}

gameMaster::gameMaster(Config config, estrategia strat) {
	assert(config.x>0); 
	assert(config.y>0); // Tamaño adecuado del tablero

    this->x = config.x;
	this->y = config.y;

	assert((config.bandera_roja.first == 1)); // Bandera roja en la primera columna
	assert(es_posicion_valida(config.bandera_roja)); // Bandera roja en algún lugar razonable

	assert((config.bandera_azul.first == x-1)); // Bandera azul en la primera columna
	assert(es_posicion_valida(config.bandera_azul)); // Bandera roja en algún lugar razonable

	assert(config.pos_rojo.size() == config.cantidad_jugadores);
	assert(config.pos_azul.size() == config.cantidad_jugadores);
	for(auto &coord : config.pos_rojo) {
		assert(es_posicion_valida(coord)); // Posiciones validas rojas
	}

	for(auto &coord : config.pos_azul) {
		assert(es_posicion_valida(coord)); // Posiciones validas rojas
	}


	this->jugadores_por_equipos = config.cantidad_jugadores;
	this->pos_bandera_roja = config.bandera_roja;
	this->pos_bandera_azul = config.bandera_azul;
    this->pos_jugadores_rojos = config.pos_rojo;
    this->pos_jugadores_azules = config.pos_azul;
	// Seteo tablero
	tablero.resize(x);
    for (int i = 0; i < x; ++i) {
        tablero[i].resize(y);
        fill(tablero[i].begin(), tablero[i].end(), VACIO);
    }


    for(auto &coord : config.pos_rojo){
        assert(es_color_libre(tablero[coord.first][coord.second])); //Compruebo que no haya otro jugador en esa posicion
        tablero[coord.first][coord.second] = ROJO; // guardo la posicion
    }

    for(auto &coord : config.pos_azul){
        assert(es_color_libre(tablero[coord.first][coord.second]));
        tablero[coord.first][coord.second] = AZUL;
    }

    tablero[config.bandera_roja.first][config.bandera_roja.second] = BANDERA_ROJA;
    tablero[config.bandera_azul.first][config.bandera_azul.second] = BANDERA_AZUL;
	this->turno = ROJO;

    cout << "SE HA INICIALIZADO GAMEMASTER CON EXITO" << endl;
    // Insertar código que crea necesario de inicialización 
	sem_init(&turno_rojo, 0, 1);
	sem_init(&turno_azul, 0, 0);
	sem_init(&mover, 0, 1);
	this->ex_jugadores_azules = pos_jugadores_azules;
	this->ex_jugadores_rojos = pos_jugadores_rojos;
	this->orden_rojo = vector<sem_t>(jugadores_por_equipos);
	this->orden_azul = vector<sem_t>(jugadores_por_equipos);
	this->strat = strat;
	for (int i = 0; i < jugadores_por_equipos; i++) sem_init(&this->orden_rojo[i], 0, 0);
	for (int i = 0; i < jugadores_por_equipos; i++) sem_init(&this->orden_azul[i], 0, 0);
	sem_init(&this->orden_rojo[0], 0, 1);
	if (strat != RR) for (int i = 1; i < jugadores_por_equipos; i++) sem_init(&this->orden_rojo[i], 0, 1);
}

void gameMaster::mover_jugador_tablero(coordenadas pos_anterior, coordenadas pos_nueva, color colorEquipo){
    assert(es_color_libre(tablero[pos_nueva.first][pos_nueva.second]));
    tablero[pos_anterior.first][pos_anterior.second] = VACIO; 
    tablero[pos_nueva.first][pos_nueva.second] = colorEquipo;
}

void gameMaster::signal(color equipo, int i){
	if (equipo == ROJO) sem_post(&orden_rojo[i]);
	else sem_post(&orden_azul[i]);
}

void gameMaster::wait(color equipo, int i){
	if (equipo == ROJO) sem_wait(&orden_rojo[i]);
	else sem_wait(&orden_azul[i]);
}

coordenadas gameMaster::getPosJugador(color equipo, int i){
	if (equipo == ROJO) return pos_jugadores_rojos[i];
	else return pos_jugadores_azules[i];
}

int gameMaster::mover_jugador(direccion dir, int nro_jugador) {
	// Chequear que la movida sea valida
	// Que no se puedan mover 2 jugadores a la vez
    // setear la variable ganador
    // Devolver acorde a la descripción
	coordenadas pos_anterior, pos_nueva;
	if (turno == ROJO) pos_anterior = pos_jugadores_rojos[nro_jugador];
	else pos_anterior = pos_jugadores_azules[nro_jugador];
	pos_nueva = proxima_posicion(pos_anterior, dir);
	if (!es_posicion_valida(pos_nueva)) return nro_ronda;

	// inicio area critica
	sem_wait(&mover);
	if((turno == AZUL && pos_nueva == pos_bandera_roja) || (turno == ROJO && pos_nueva == pos_bandera_azul)) {sem_post(&mover); ganador = turno; for(int i = 0; i < jugadores_por_equipos; i++) {signal(AZUL, i); signal(ROJO, i);} return 0;}
	if(termino_juego()) {sem_post(&mover); return nro_ronda*(ganador == turno);}
	if (!es_color_libre(en_posicion(pos_nueva))) {sem_post(&mover); return nro_ronda;}
	else mover_jugador_tablero(pos_anterior, pos_nueva, turno);
	if(turno == ROJO) pos_jugadores_rojos[nro_jugador] = pos_nueva;
	else pos_jugadores_azules[nro_jugador] = pos_nueva;
	sem_post(&mover);
	// fin area critica

	return nro_ronda;
}


void gameMaster::termino_ronda(color equipo) {
	// FIXME: Hacer chequeo de que es el color correcto que está llamando
	// FIXME: Hacer chequeo que hayan terminado todos los jugadores del equipo o su quantum (via mover_jugador)
	assert(turno == equipo);

	// deteccion de empate:
	if (ganador == INDEFINIDO) {
		if (turno == ROJO) {
			if (pos_jugadores_rojos == ex_jugadores_rojos & flag_empate) {ganador = EMPATE; for(int i = 0; i < jugadores_por_equipos; i++) {signal(AZUL, i); signal(ROJO, i);}}
			else if (pos_jugadores_rojos == ex_jugadores_rojos) flag_empate = true;
			else flag_empate = false;
		} else {
			if (pos_jugadores_azules == ex_jugadores_azules & flag_empate) {ganador = EMPATE; for(int i = 0; i < jugadores_por_equipos; i++) {signal(AZUL, i); signal(ROJO, i);}}
			else if (pos_jugadores_azules == ex_jugadores_azules) flag_empate = true;
			else flag_empate = false;
		}
	}
	// actualizar historial de movimientos (se usa para deteccion de empate)
	if (turno == ROJO) ex_jugadores_rojos = pos_jugadores_rojos;
	else ex_jugadores_azules = pos_jugadores_azules;

	nro_ronda++;
	turno = (equipo == ROJO) ? AZUL : ROJO;

	// reestablecer semaforos
	if (strat == RR) signal(turno, 0);
	else for(int i = 0; i < jugadores_por_equipos; i++) signal(turno, i);
}

bool gameMaster::termino_juego() {
	return ganador != INDEFINIDO;
}

coordenadas gameMaster::proxima_posicion(coordenadas anterior, direccion movimiento) {
	// Calcula la proxima posición a moverse (es una copia) 
	switch(movimiento) {
		case(ARRIBA):
			anterior.second--; 
			break;

		case(ABAJO):
			anterior.second++;
			break;

		case(IZQUIERDA):
			anterior.first--;
			break;

		case(DERECHA):
			anterior.first++;
			break;
	}
	return anterior; // está haciendo una copia por constructor
}

