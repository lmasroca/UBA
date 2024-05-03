# Resumen
Este trabajo trata sobre un problema físico, en el cual se debe encontrar la isoterma de 500 grados Celsius dentro de un horno, sabiendo la temperatura del centro del horno y teniendo las temperaturas de los sensores en las paredes externas del horno.
Se discretiza el horno, se arma un sistema de ecuaciones y se resuelve en C++ con distintos algoritmos (Eliminación Gaussiana y descomposición LU) consiguiendo las temperaturas en distintos lugares del horno.
Luego se aproxima la ubicación de la isoterma usando distintos métodos (aproximación lineal y truncamiento) en Python. Se provee código en Python para generar valores aleatorios de temperaturas para los sensores de acuerdo a una distribución normal o uniforme y código para graficar distintos experimentos.
El objetivo es comparar la performance de distintos algoritmos de resolución de sistemas y sus versiones optimizadas, así como experimentar con distintos criterios de peligro, entre otras cosas.

# Instrucciones
Para compilar el .cpp incluimos un Makefile con make y make clear, para utilizar en conjunto con Python:
La opción más básica (usamos la versión LUO por predeterminado en tp1.cpp si no se edita el código) es compilar el tp1 usando make y usar el ejecutable tp1, luego cuando Python pregunta que algoritmo ejecutar dejamos el campo vacío (enter).
Sin embargo, para diferenciar algoritmos recomendamos compilar un ejecutable para cada algoritmo que va a ser ejecutado (de los 4 posibles) y nombrarlos tp1EG, tp1EGO, tp1LU, tp1LUO (según corresponda), para cambiar el algoritmo a compilar modificar los dos valores booleanos que se encuentran en las primeras dos líneas de la función main de tp1.cpp en las líneas 260 y 261 (EG indica si se usara EG o LU, mientras que O indica si se usaran las versiones optimizadas del algoritmo seleccionado en la línea anterior).


Para utilizar los ejecutables de C++ por consola usar ./tp1 radio_interno radio_externo cant_radios cant_angulos valor_isoterma(esto no se utiliza) ninst [2*cant_angulos*ninst valores decimales separados por espacios simples (sin pasar de línea para cada ninst)].


Ejemplos:
```
./tp1 10 100 20 5 500 1 1500 1500 1500 1500 1500 0 0 0 0 0
```
```
./tp1 10 100 20 5 500 2 1500 1500 1500 1500 1500 0 0 0 0 0 1500 1500 1500 1500 1500 0 0 0 0 0
```

Los resultados se devuelven en formato .csv en la carpeta csv (ver esquema de carpetas) más abajo


El esquema de carpetas es el siguiente:
/
    cpp
        tp1.cpp
        Makefile
        ejecutables compilados de C++ para Linux (tp1, tp1EG, etc. los que sean necesarios)
    py
        main.py (generar experimentos, peligro e isoterma)
        graficos.py (gráficos de tiempo)
        requirements.txt (para usar con pip para instalar los paquetes requeridos)
    csv
        tiempo_instancias (carpeta que contiene los csv con la información para los plots del segundo experimento)
        Ruta predeterminada para los archivos csv de salida de Python y C++.
    informe
        Informe realizado para este trabajo práctico, explicando los experimentos realizados y sus resultados, entre otras cosas.


Para correr experimentos con Python (luego de compilar al menos una versión): desde la terminal, localizarse sobre la carpeta py, y ejecutar python3 main.py, deberán descargarse las los paquetes requeridos, se incluye un requirements.txt para usar con pip.


```
pip install -r requirements.txt
```
