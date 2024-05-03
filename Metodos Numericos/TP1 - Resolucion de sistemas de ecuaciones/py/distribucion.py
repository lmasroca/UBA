import numpy as np

cantidad_angulos = int(input("Cantidad de angulos: "))

interno = np.full(cantidad_angulos, 1500)

normal = np.random.normal(150,25,cantidad_angulos)
paramNormal = np.concatenate((interno, normal), dtype="str")

with open("../csv/parametrosNormal.csv", 'w') as f:
    for value in paramNormal:
        f.write(value +  " ")

print(paramNormal)

uniforme = np.random.uniform(0,150,cantidad_angulos)
paramUnif = np.concatenate((interno, uniforme), dtype="str")

with open("../csv/parametrosUniforme.csv", 'w') as f:
    for value in paramUnif:
        f.write(value +  " ")

print(paramUnif)