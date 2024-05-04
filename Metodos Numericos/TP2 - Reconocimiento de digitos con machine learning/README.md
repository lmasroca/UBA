# Resumen

Se construye un clasificador de dígitos usando machine learning. Se utiliza la técnica de los k vecinos más cercanos (kNN) y se evalúa como cambia la performance al agregar análisis de componentes principales (PCA). El objetivo de este trabajo es comparar la performance, tanto en tiempo como en accuracy (entre otras métricas), de ambas estrategias. Ambas estrategias fueron implementadas en C++. Adicionalmente, se realizó código en Python para experimentar con distintos hiperparámetros y comparar las estrategias. Finalmente, se participó en la [competencia de Kaggle](https://www.kaggle.com/c/digit-recognizer) y se obtuvo % 97.492 de accuracy.

Si bien el código se realizó para reconocimiento de dígitos, con mínimos cambios, se puede usar para clasificación de caracteres (OCR) si se entrena con caracteres en lugar de dígitos.

# Instrucciones

Para compilar el .cpp incluimos un Makefile con make y make clear, para utilizar en conjunto con Python:
Compilar un ejecutable (preferiblemente compilar tp2OPT y renombrar a tp2 para evitar problemas en algunas funciones de Python, probablemente haya que editar el Makefile) o utilizar el ejecutable incluido.

Para utilizar los ejecutables de C++ por consola usar:

```
./tp2 PCA_bool k votacionPesada_bool alpha epsilon train.csv test.csv output.csv [guardarKMasCercanosBool] [guardarAvectsBool] [leerKMasCercanos] [leerAVect numFold]
```

Ponemos entre corchetes los [argumentos opcionales], usados únicamente en caso de compilar tp2OPT.

Ejemplo:

```
./tp2 0 5 0 30 1e-6 train.csv test.csv output.csv
```

En el archivo tp2OPT.cpp se encuentra más claro como funcionan estos parámetros opcionales.

Entregamos tp2.cpp y tp2OPT.cpp, ya que el primero es más fácil de leer y entender las ideas de PCA y lo relevante de este TP.

El esquema de carpetas es el siguiente:

```
/
└────cpp
│    |  tp2.cpp
|    |  tp2OPT.cpp
|    |  Makefile
|    |  tp2 (ejecutable de C++ compilado para linux de la version OPT)
|    └───Eigen
|           Se usa la implementacion de vectores de Eigen, ya que proveen mas funcionalidades que los vectores de C++.
└────py
|       main.py
|       notebooks de experimentos, imagenes, plots y tablas.
|       requirements.txt (para usar con pip para instalar los paquetes requeridos)
|       (opcional: build_csv.py y metricas.py)
└────csv
|       Poner aqui los csv que van a ser leidos desde Python o C++.
|       Aqui van a aparecer los archivos de salida de C++ y Python.
└────im
|       Imagenes realizadas por el grupo y sus distintas transformaciones (invertir colores, numerar, etc.).
└────informe
        Informe del trabajo
```

Para correr experimentos con Python: Pueden usar un Jupyter notebook e importar las funciones de main.py. En el encontrarán las funciones que permiten calcular kNN y kappa de Cohen. En cada una de estas funciones se encuentra detallada su implementación, así como también sus parámetros y su retorno. Para los plots, se encuentra el notebook plots.ipynb y por último las tablas en tablas.ipynb.

Además, contamos con build_csv.py que nos permite construir csvs que tengan un formato legible para poder ser importados por pandas. Y por último, el metricas.py cuyo único propósito es comparar los resultados de las métricas calculadas por nuestro clasificador contra el clasificador de sklearn. Se incluye un requirements.txt para usar con pip para instalar las dependencias.

```
pip install -r requirements.txt
```