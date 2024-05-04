# Resumen

En este trabajo tiene dos partes:

En la primera parte se implementan los algoritmos necesarios para pasar de una expresión regular a un autómata finito determinístico de estados mínimos que reconoce el mismo lenguaje (expresión regular $\rightarrow$ AFND $\rightarrow$ AFD $\rightarrow$ AFD Mínimo) y se realizan distintos experimentos comparando una implementación naive (recursivo) de este proceso contra nuestra implementación.

En la segunda parte se una construye una gramática y se usa un parser LALR(1) para pasar de un string que representa una expresión regular a una expresión regular en Python.

# Aclaraciones

## Sintaxis

La sintaxis usada para las expresiones regulares en strings de la segunda parte del trabajo es la siguiente:
- Cadena vacía es el string vacío (en caso de usarla como operando de algún operador, encerrarla entre paréntesis).
- Los siguientes caracteres se encuentran reservados:  |, *, +, ?, \, (, ), [, ]. Todos los caracteres no reservados deben interpretarse como la expresión regular correspondiente a dicho carácter. Si se quiere usar un carácter reservado como un carácter normal, se debe anteponer \ como carácter de escape.
- Operadores binarios entre R y S:
  - Unión: R|S (O bien R o bien S).
  - Concatenación: RS (R seguida de S).
- Operadores unarios sobre R:
  - Kleene: R$\star$ (Cero o más apariciones de R).
  - Clausura positiva: R+ (Una o más apariciones de R).
  - Opcional: R? (Cero o una aparición de R).
  - Cuantificador simple: R{$n$} (Exactamente $n$ aparición de R).
  - Cuantificador doble: R{$n$,$m$} (Entre $n$ y $m$ apariciones de R).
- Clases de caracteres: expresión de la forma [$x_1x_2 . . . x_n$], donde cada $x_i$ puede ser:
  - Un carácter cualquiera (no reservado).
  - Un rango de caracteres, de la forma $x$-$y$, donde $x$ e $y$ son caracteres cualesquiera (no reservados) con $x$ anterior a $y$ en orden lexicográfico, representando todos los caracteres entre $x$ e $y$ en dicho orden.
- Clases de caracteres especiales:
  - \d: Cualquier dígito decimal.
  - \w: Cualquier carácter alfanumérico, más el guion bajo.

## Precedencia y asociatividad

La siguiente tabla muestra la precedencia y asociatividad de los operadores (ordenados de mayor a menor precedencia):

| Operador        | Sintaxis                 | Precedencia | Asociatividad |
|-----------------|--------------------------|-------------|---------------|
| Cuantificadores | R*, R+, R?, R{n}, R{n,m} | 3           | -             |
| Concatenación   | RS                       | 2           | a izquierda   |
| Unión           | R\|S                     | 1           | a izquierda   |

Pueden usarse paréntesis para alterar la precedencia de los operadores.

# Instrucciones

## Estructura del repositorio

El repositorio contiene los siguientes directorios:
- `tlengrep`: contiene el código fuente.
- `informe`: contiene los informes.

## Modo de ejecución

Todos los comandos que se mencionan a continuación deben ejecutarse desde el
directorio `tlengrep`.

### Pasos previos

1. (Opcional) Crear un entorno virtual de Python:
   ```bash
   python3 -m venv venv
   ```
   y activarlo:
   ```bash
   source venv/bin/activate
   ```
   (para desactivarlo, ejecutar `deactivate`).

2. Instalar las dependencias:
   ```bash
    pip install -r requirements.txt
    ```

### Ejecución del programa
El programa se ejecuta con el comando:
```bash
python3 tlengrep.py [expresión regular] [archivo de entrada]
```

- El argumento `expresión regular` indica la expresión regular a buscar y es
  obligatorio.
- El argumento `archivo de entrada` indica el archivo en el que se debe buscar
  y es opcional. De no ser especificado, se lee de la entrada estándar.

El programa admite las siguientes opciones:
- `-h`, `--help`: muestra un mensaje de ayuda y termina.
- `-m`, `--module [módulo]`: permite cargar una expresión regular ya parseada
  desde un módulo de Python. De usarse esta opción, no se debe especificar
  la expresión regular como argumento.
- `-n`, `--naive`: utiliza la implementación naive.

## Ejecución de los tests
Para ejecutar los tests, utilizar los siguientes comandos:
- Para la primera parte, ejecutar:
  ```bash
  pytest -k test_regex.py
  ```
- Para la segunda parte, ejecutar:
  ```bash
  pytest -k test_parser.py
  ```