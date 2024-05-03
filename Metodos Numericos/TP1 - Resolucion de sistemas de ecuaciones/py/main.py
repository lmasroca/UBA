import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import subprocess
import statistics as stats


algoritmo = input("EG - LU - EGO - LUO: ").upper()

comando = [f'../cpp/tp1{algoritmo}']

radio_int = int(input("Valor de radio interno: "))
comando.append(str(radio_int))

radio_ext = int(input("Valor de radio externo: "))
comando.append(str(radio_ext))

cantidad_radios = int(input("Cantidad de Radios: "))
comando.append(str(cantidad_radios))

cantidad_angulos = int(input("Cantidad de angulos: "))
comando.append(str(cantidad_angulos))

comando.append('500')

instancias = int(input("Cantidad de instancias: "))
comando.append(str(instancias))
mismas = int(input("Todas las instancias iguales? Si[0] - No[1]: "))
if mismas == 1:
    for i in range(instancias):
        radios_internos = int(input(f"Valor de radios internos ninst{i}(1500[0] - normal[1] - uniforme[2]): "))

        if radios_internos == 0:
            radios_internos = np.full(cantidad_angulos, '1500')
        elif radios_internos == 1:
            esperanza = int(input("Esperanza: "))
            varianza = int(input("Varianza: "))
            radios_internos = np.random.normal(esperanza, varianza, cantidad_angulos).astype("str")
        else:
            minimo = int(input("Minimo: "))
            maximo = int(input("Maximo: "))
            radios_internos = np.random.uniform(minimo, maximo, cantidad_angulos).astype("str")

        comando.extend(list(radios_internos))

        radios_externos = int(input(f"Valor de radios externos ninst{i}(mismo valor[0] - normal[1] - uniforme[2]): "))

        if radios_externos == 0:
            valor = input("Escrbir valor: ")
            radios_externos = np.full(cantidad_angulos, valor)
        elif radios_externos == 1:
            esperanza = int(input("Esperanza: "))
            varianza = int(input("Varianza: "))
            radios_externos = np.random.normal(esperanza, varianza, cantidad_angulos).astype("str")
        else:
            minimo = int(input("Minimo: "))
            maximo = int(input("Maximo: "))
            radios_externos = np.random.uniform(minimo, maximo, cantidad_angulos).astype("str")

        comando.extend(list(radios_externos))
else:
    valor_internos = int(input(f"Valor de radios internos (1500[0] - normal[1] - uniforme[2]): "))
    valor_externos = int(input(f"Valor de radios externos (mismo valor[0] - normal[1] - uniforme[2]): "))
    if valor_internos == 0:
        radios_internos = np.full(cantidad_angulos, '1500')
    elif valor_internos == 1:
        esperanza_interna = int(input("Esperanza: "))
        varianza_interna = int(input("Varianza: "))
    else:
        minimo_interno = int(input("Minimo: "))
        maximo_interno = int(input("Maximo: "))

    if valor_externos == 0:
        valor = input("Escrbir valor: ")
    elif valor_externos == 1:
        esperanza_externa = int(input("Esperanza: "))
        varianza_externa = int(input("Varianza: "))
    else:
        minimo_externo = int(input("Minimo: "))
        maximo_externo = int(input("Maximo: "))

    for i in range(instancias):
        if valor_internos == 0:
            radios_internos = np.full(cantidad_angulos, '1500')
        elif valor_internos == 1:
            radios_internos = np.random.normal(esperanza_interna, varianza_interna, cantidad_angulos).astype("str")
        else:
            radios_internos = np.random.uniform(minimo_interno, maximo_interno, cantidad_angulos).astype("str")
        comando.extend(list(radios_internos))

        if valor_externos == 0:
            radios_externos = np.full(cantidad_angulos, valor)
        elif valor_externos == 1:
            radios_externos = np.random.normal(esperanza_externa, varianza_externa, cantidad_angulos).astype("str")
        else:
            radios_externos = np.random.uniform(minimo_externo, maximo_externo, cantidad_angulos).astype("str")

        comando.extend(list(radios_externos))



print(f"Ejecutamos el binario tp1{algoritmo} con los siguientes parametros")
print(comando)

result = subprocess.run(comando, stdout=subprocess.PIPE, encoding='ascii')
print(result.stdout.split('\n')[0])
duracion = result.stdout.split(' ')[4]
with open(f"../csv/tiempo_por_instancias{algoritmo}.csv", 'a') as f:
    f.write(duracion + ',' + str(instancias) + ',')
    f.close()


''' ------------------- Aproximacion de la isoterma ------------------- '''

matrices = {}
for i in range(instancias):
    nombre_de_archivo = f"temp{algoritmo}{i}"

    array_mat = np.genfromtxt("../csv/" + nombre_de_archivo + ".csv", delimiter=',')

    matriz = np.reshape(array_mat, (cantidad_radios, cantidad_angulos)).transpose()

    matrices[nombre_de_archivo] = matriz

# print(matrices)

# print("Matriz:")
# print(matriz)

delta_radio = (radio_ext - radio_int) / (cantidad_radios - 1)

# Devuelve la posicion mas cercana al valor deseado redondeando para abajo
def mas_cercano(array, value):
    pos_radio_mas_cerca = (np.abs(array - value)).argmin()
    return pos_radio_mas_cerca

def calcular_isoterma_truncado(matriz, iso_temp):
    vector_isoterma = []
    for fila in matriz:
        distancia_radio = radio_int + mas_cercano(fila, iso_temp) * delta_radio
        vector_isoterma.append(distancia_radio)
    return vector_isoterma

def calcular_isoterma_lineal(matriz, iso_temp):
    vector_isoterma = []
    for fila in matriz:
        pos = mas_cercano(fila, iso_temp)
        if fila[pos] > iso_temp:
            distancia_radio = radio_int + pos * delta_radio
            if pos >= cantidad_radios-1:
                radio_restante = 0
            else:
                radio_restante = delta_radio * \
                    (fila[pos] - iso_temp) / (fila[pos] - fila[pos+1])
        elif fila[pos] < iso_temp:
            distancia_radio = radio_int + (pos-1) * delta_radio
            radio_restante = delta_radio * \
                (fila[pos-1] - iso_temp) / (fila[pos-1] - fila[pos])
        else:
            distancia_radio = radio_int + pos * delta_radio
            radio_restante = 0
        vector_isoterma.append(distancia_radio + radio_restante)
    return vector_isoterma

print("...Resolucion de Isoterma...")
prueba = int(input("calculo lineal [0] - truncado [1]: "))
temp = int(input("Numero de Temperatura a aproximar: "))
isotermas = {}
if prueba == 0:
    for nombre, matriz in matrices.items():
        isotermas[nombre] = calcular_isoterma_lineal(matriz, temp)
else:
    for nombre, matriz in matrices.items():
        isotermas[nombre] = calcular_isoterma_truncado(matriz, temp)

df_isotermas = pd.DataFrame(isotermas)
print(df_isotermas)
df_isotermas.to_csv(f"../csv/isotermas{algoritmo}.csv")

# --- Calculo del criterio de peligro ---
criterio_peligro_alguno = {}
criterio_peligro_mediana = {}
for nombre, iso in isotermas.items():
    radio_maximo = np.max(iso)
    radio_mediana_alta = stats.median_high(iso)
    radio_total = radio_ext - radio_int
    if radio_total * 0.8 <= radio_maximo:
        criterio_peligro_alguno[nombre] = 'peligroso'
    else:
        criterio_peligro_alguno[nombre] = 'seguro'
    if radio_total * 0.8 <= radio_mediana_alta:
        criterio_peligro_mediana[nombre] = 'peligroso'
    else:
        criterio_peligro_mediana[nombre] = 'seguro'
print("Criterio de peligro minimo:")
print(pd.Series(criterio_peligro_alguno))
print("Criterio de peligro mediana alta:")
print(pd.Series(criterio_peligro_mediana))


# grafico de multiples isotermas
muestra_iso = int(input("Mostrar  las isotermas juntas [0] o separadas [1]: "))
if muestra_iso == 0:
    fig, ax = plt.subplots(subplot_kw={'projection': 'polar'})
    for nombre, iso in isotermas.items():
        cantidad_puntos = len(iso) 
        r = np.array(iso)
        # print("Isoterma:")
        # print(r)
        # r[-1] = r[0] # Solo para que la isoterma se "pegue" bien al dar la vuelta
        theta = np.linspace(0, 2*np.pi, cantidad_puntos)

        ax.plot(theta, r, label = nombre)

    ax.set_rmax(radio_ext)
    ax.set_rticks(np.linspace(radio_int, radio_ext, 5))
    ax.set_rlabel_position(-22.5)
    ax.grid(True)
    ax.legend(bbox_to_anchor=(1.05, 1.0),loc="upper right")
    ax.set_title("Isoterma")

    plt.show()
else:
    for nombre, iso in isotermas.items():
        cantidad_puntos = len(iso) 
        r = np.array(iso)
        # print("Isoterma:")
        # print(r)
        # r[-1] = r[0] # Solo para que la isoterma se "pegue" bien al dar la vuelta
        theta = np.linspace(0, 2*np.pi, cantidad_puntos)

        fig, ax = plt.subplots(subplot_kw={'projection': 'polar'})
        ax.plot(theta, r)
        ax.set_rmax(radio_ext)
        ax.set_rticks(np.linspace(radio_int, radio_ext, 5))
        ax.set_rlabel_position(-22.5)
        ax.grid(True)
        ax.set_title("Isoterma")
        plt.show()

# Grafico de temperaturas
mostrar_temps = int(input("Mostrar grafico de temperaturas? si [0] - No [1]: "))
if mostrar_temps == 0:
    for nombre, iso in isotermas.items():
        theta = np.tile(np.linspace(0, 2*np.pi, cantidad_angulos), (cantidad_radios, 1)).transpose()
        r = np.tile(np.linspace(0, 1, cantidad_radios), (cantidad_angulos, 1))
        z = matrices[nombre]

        fig, ax = plt.subplots(subplot_kw={'projection': 'polar'})

        grafico = ax.pcolor(theta, r, z, cmap='jet')
        ax.set_title('Alto horno')

        ax.set_yticklabels([])

        fig.colorbar(grafico)
        plt.show()