# Resumen

En este trabajo se implementó el procedimiento de regresión localmente pesada (LOESS) en Python. Se implementó la versión multivariada del algoritmo de regresión univariada presentado en el [paper de Cleveland del 1979](https://home.engineering.iastate.edu/~shermanp/STAT447/Lectures/Cleveland%20paper.pdf), adicionalmente se recreó la experimentación y los gráficos de la sección 5 del [paper de Cleveland del 1988](https://sites.stat.washington.edu/courses/stat527/s14/readings/Cleveland_Delvin_JASA_1988.pdf). Se experimentó con distintos parámetros, funciones de pesos y sobre datos sintéticos.

# Instrucciones

Se incluye un requirements.txt para instalar los módulos requeridos para Python usando pip.

```bash
pip install -r requirements.txt
```

Se incluyen los siguientes archivos:
- requirements.txt
- main.py: Definimos las distintas funciones necesarias para usar en los notebooks (fit, funciones de peso, predict, etc.).
- data_sintetica.ipynb: Notebook para generar datos sintéticos y experimentar con ellos.
- datasintetica.csv: Datos generados aleatoriamente con distintas distribuciones usando el notebook mencionado anteriormente.
- exp_pesos.ipynb: Se experimentó con distintas funciones de pesos.
- params.ipynb: Se experimentó con distintos parámetros (cantidad de vecinos y grado).
- seccion5.ipynb: Notebook para recrear los gráficos de la sección 5 del [paper 1988](https://sites.stat.washington.edu/courses/stat527/s14/readings/Cleveland_Delvin_JASA_1988.pdf).
- TP3MN - Informe: Informe del trabajo realizado, explicando los experimentos realizados y los resultados obtenidos.
- dataset.csv: Dataset para recrear la experimentación de la sección 5 del [paper 1988](https://sites.stat.washington.edu/courses/stat527/s14/readings/Cleveland_Delvin_JASA_1988.pdf).