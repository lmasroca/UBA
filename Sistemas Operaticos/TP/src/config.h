#ifndef CONFIG_H
#define CONFIG_H

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "definiciones.h"

using namespace std;
class Config{
    public:
        Config(string configName);
        vector<coordenadas> pos_rojo, pos_azul;
        coordenadas bandera_roja, bandera_azul;
        int cantidad_jugadores;
        int x,y;

    private:

};
#endif // CONFIG_H
