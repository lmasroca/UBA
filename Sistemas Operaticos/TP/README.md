# Resumen

En este trabajo se implementa un juego de tablero de captura la bandera por equipos (2 equipos) en C++ utilizando concurrencia. Se implementan y comparan distintas estrategias de juego (estrategias de scheduling) para los equipos. Adicionalmente, se valida que el funcionamiento del juego no se vea afectado por la concurrencia, verificando el correcto uso de distintas herramientas como mutex, semáforos y variables atómicas. Finalmente, se compara la performance de utilizar concurrencia o no hacerlo para buscar una bandera en tableros de distintos tamaños y se experimenta con distintas cantidades de threads.

# Instrucciones

El tp se puede compilar y correr usando el script `run.sh` o alternativamente usando:

```
mkdir -p build && cd build && cmake ../ && make
```

y luego buscando el ejecutable `test_tp_sistemas` en la carpeta `build` (posiblemente el script no tenga permisos de ejecución, usar sudo chmod +x).

Se provee un requirements.txt para poder instalar los módulos necesarios para correr el notebook de Python de los experimentos.

```
pip install -r requirements.txt
```

En cuanto a los experimentos, se provee un notebook de Python que usa subprocess para correr un ejecutable de C++ que contiene solamente la búsqueda de la bandera pasando parámetros por consola. Para compilar ese ejecutable se puede usar make en la carpeta de experimentos.