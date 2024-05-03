import numpy as np
import matplotlib.pyplot as plt

t_EG = np.genfromtxt("../csv/tiempo_instancias/tiempo_por_instanciasEG.csv", delimiter=',', dtype='int')
t_EG = np.array([t_EG[2*i] for i in range(5)])


t_EGO = np.genfromtxt("../csv/tiempo_instancias/tiempo_por_instanciasEGO.csv", delimiter=',', dtype='int')
t_EGO = np.array([t_EGO[2*i] for i in range(5)])


t_LU = np.genfromtxt("../csv/tiempo_instancias/tiempo_por_instanciasLU.csv", delimiter=',', dtype='int')
t_LU = np.array([t_LU[2*i] for i in range(5)])


t_LUO = np.genfromtxt("../csv/tiempo_instancias/tiempo_por_instanciasLUO.csv", delimiter=',', dtype='int')
t_LUO = np.array([t_LUO[2*i] for i in range(5)])

print(t_EG)
print(t_EGO)
print(t_LU)
print(t_LUO)


fig, ax = plt.subplots()
instancias = [2, 8, 18, 32, 50]
tiempo = {'EG': t_EG, 'EGO': t_EGO, 'LU': t_LU, 'LUO': t_LUO}
# ax.plot(instancias, tiempo['EG'], label = 'EG', marker = '.')
ax.plot(instancias, tiempo['EGO'], label = 'EGO', marker = 'v')
ax.plot(instancias, tiempo['LU'], label = 'LU', marker = 's')
ax.plot(instancias, tiempo['LUO'], label = 'LUO', marker = 'o')
ax.set_xlabel("Instancias", fontdict = {'fontsize':14, 'fontweight':'bold'})
ax.legend(bbox_to_anchor=(1.05, 1.0), loc = 'upper right')
ax.set_xticks(instancias)
ax.set_ylabel("Tiempo en milisegundos")
ax.grid(axis = 'y', color = 'gray', linestyle = 'dashed')
ax.set_title('Evolución del tiempo de cómputo entre Optimizados', loc = "left", fontdict = {'fontsize':14, 'fontweight':'bold'})
plt.show()

fig, ax = plt.subplots()
instancias = [2, 8, 18, 32, 50]
tiempo = {'EG': t_EG, 'EGO': t_EGO, 'LU': t_LU, 'LUO': t_LUO}
ax.plot(instancias, tiempo['EG'], label = 'EG', marker = '.')
ax.plot(instancias, tiempo['EGO'], label = 'EGO', marker = 'v')
ax.plot(instancias, tiempo['LU'], label = 'LU', marker = 's')
ax.plot(instancias, tiempo['LUO'], label = 'LUO', marker = 'o')
ax.set_xlabel("Instancias", fontdict = {'fontsize':14, 'fontweight':'bold'})
ax.legend(bbox_to_anchor=(1.05, 1.0), loc = 'upper right')
ax.set_xticks(instancias)
ax.set_ylabel("Tiempo en milisegundos")
ax.grid(axis = 'y', color = 'gray', linestyle = 'dashed')
ax.set_title('Evolución del tiempo de cómputo en milisegundos', loc = "left", fontdict = {'fontsize':14, 'fontweight':'bold'})
plt.show()

fig, ax = plt.subplots()
instancias = [2, 8, 18, 32, 50]
tiempo = {'EG': t_EG/1000, 'EGO': t_EGO/1000, 'LU': t_LU/1000, 'LUO': t_LUO/1000}
print(tiempo)
ax.plot(instancias, tiempo['EG'], label = 'EG', marker = 'X')
ax.plot(instancias, tiempo['EGO'], label = 'EGO', marker = '^')
ax.plot(instancias, tiempo['LU'], label = 'LU', marker = 's')
ax.plot(instancias, tiempo['LUO'], label = 'LUO', marker = 'o')
ax.set_xlabel("Instancias", fontdict = {'fontsize':14, 'fontweight':'bold'})
ax.legend(bbox_to_anchor=(1.05, 1.0), loc = 'upper right')
ax.set_xticks(instancias)
ax.set_ylabel("Tiempo en segundos", fontdict = {'fontsize':10})
ax.set_yscale('log')
ax.grid(axis = 'y', color = 'gray', linestyle = 'dashed')
ax.set_title('Evolución del tiempo de cómputo en segundos', loc = "left", fontdict = {'fontsize':14, 'fontweight':'bold'})
plt.show()