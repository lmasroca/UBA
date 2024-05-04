#ifndef GAMEMASTER_H
#define GAMEMASTER_H
#include <tuple>
#include <cstdio>
#include <vector>
#include <mutex>
#include <semaphore.h>
#include "definiciones.h"
#include "config.h"

using namespace std;

class gameMaster {
private:
    // Atributos Privados
    int nro_ronda = 1; // si vamos a devolver 0 si gana, pq esta arranca en 0? asi en la primer ronda el equipo 0 gana directo
    int x, y, jugadores_por_equipos;
    vector<vector<color>> tablero;
    vector<coordenadas> pos_jugadores_azules, pos_jugadores_rojos;
    coordenadas pos_bandera_roja, pos_bandera_azul;
    color turno;
    estrategia strat;
    // nuevo
    sem_t mover;
    vector<coordenadas> ex_jugadores_azules, ex_jugadores_rojos;
    bool flag_empate = false;

    // Métodos privados
    color obtener_coordenadas(coordenadas coord);
    void mover_jugador_tablero(coordenadas pos_anterior, coordenadas pos_nueva, color colorEquipo);

public:
    // Atributos públicos
    gameMaster(Config config, estrategia strat);
    void termino_ronda(color equipo); // Marca que un jugador terminó la ronda
    int mover_jugador(direccion dir, int nro_jugador);
    color ganador = INDEFINIDO;
    // nuevo
    vector<sem_t> orden_rojo;
	vector<sem_t> orden_azul;

    // Métodos públicos
    bool termino_juego();
	int getTamx();
	int getTamy();
    static int distancia(coordenadas pair1, coordenadas pair2);
    sem_t turno_rojo, turno_azul; // FIXME: Al principio necesito entrar como azul, luego puedo hacerlo por el método termino_ronda....
    color en_posicion(coordenadas coord);
    bool es_posicion_valida(coordenadas pos);
    bool es_color_libre(color color_tablero);
	coordenadas proxima_posicion(coordenadas anterior, direccion movimiento); // Calcula la proxima posición a moverse	
    // OJO: no usamos mas turno_rojo y turno_azul, usamos orden de cada equipo
	// nuevo
    void signal(color equipo, int i);
    void wait(color equipo, int i);
    coordenadas getPosJugador(color equipo, int i);
};

#endif // GAMEMASTER_H
