# Resumen

En este trabajo se resuelven 4 problemas distintos usando 4 técnicas distintas (DFS, AGMs, Camino Mínimo y SRD) en C++. Tres de estos problemas se presentaron en el juez online de UVa, se pueden ver los resultados [aquí](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_authorstats&userid=1331537). Los problemas son:
- Depth First Search: [Hedge Mazes](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3785).
- Árboles Generadores Mínimos: [Tour Belt](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3706).
- Caminos Mínimos: [USHER](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3674).
- Sistemas de Restricciones de Diferencias: Dado un sistema de ecuaciones de $n$ variables $x_i$ donde cada ecuación es de la forma $x_i - x_j \leq c$ y una lista $D$ de $m$ números enteros ordenados ascendentemente, decidir si existe una asignación de las variables a elementos en $D$ que satisfaga las ecuaciones del sistema. Se resolvió implementando el algoritmo de [fishburn 2002](https://www.sciencedirect.com/science/article/abs/pii/S0020019001002678).

# Archivos

Se provee:
- 4 archivos .cpp, uno por cada ejercicio.
- Un Makefile para compilar los 4 ejecutables.
- Un informe en el cual se explican todos los algoritmos planteados y su complejidad. Adicionalmente, hay argumentos/demostraciones para justificar su correctitud y/o complejidad temporal.

# Instrucciones

## [Ejercicio 1](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3785)

## [Ejercicio 2](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3785)

## [Ejercicio 3](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3674)

## Ejercicio 4

El input comienza con una línea que contiene la cantidad $T$ de casos de tests. Luego siguen los $T$ casos.
La primera línea de cada caso contiene 3 enteros $k, n$ y $m$, separados por espacios, indicando respectivamente la cantidad de ecuaciones, la cantidad de variables, y el tamaño del conjunto $D$.
Las siguientes $k$ líneas describen, cada una, una de las ecuaciones del sistema. Estas son descritas por 3 números enteros $a, b$ y $c$, donde $a$ y $b$ denotan índices de variables. Las variables están numeradas de 1 a $n$, por lo que $1 \leq a, b \leq n$. Los 3 números $a, b$ y $c$ en ese orden representan la ecuación $x_a − x_b \leq c$.
Después se sigue una línea con $m$ números enteros $d_1 . . . d_m$ separados por espacios y ordenados de menor a mayor. Estos representan al conjunto $D$.