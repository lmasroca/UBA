import numpy as np
from scipy.spatial import distance
from heapq import nsmallest
from heapq import nlargest
from sklearn.preprocessing import PolynomialFeatures

# Funcion tricubica
def W(u):
    return (0 <= u and u < 1) * (1 - u**3)**3

# Funcion bicuadratica
def B(x):
    return (-1 < x and x < 1) * (1 - x**2)**2

# Funcion de peso alternativa a tricubica
def Z(x):
    return ((0 <=  x and x < 1) * (1-x**(1/3))**(1/3)).real

# Distancia euclideana
def p(x, y):
    return distance.euclidean(x, y)

# Funcion para obtener los q vecinos mas cercanos de x en X
def neighbors(X, x, q):
    return np.array(nsmallest(q, X, key=lambda xi: distance.euclidean(xi, x)))

# Distancia euclideana mas grande entre xi en X_V y x, en este caso tomamos X_V como el vecindario de tamaño q de x en el X original esto se calcula con neighbors(X, x, q)
def d(X_V, x):
    return distance.euclidean(np.array(nlargest(1, X_V, key=lambda xi: distance.euclidean(xi, x)))[0], x)

# Funcion para armar la matriz de pesos W asignada a x sobre X (esto nos permite filtrar el vecindario y asignar pesos a cada xi en X)
def armarW(X, x, q, weight="W"):
    d_x = d(neighbors(X, x, q), x) #distancia mas grande entre los q vecinos mas cercanos de x
    v = [eval(weight + "(" + str(p(X[i], x)/d_x) + ")") for i in range(len(X))]
    return np.diag(np.array(v))

# Funcion para armar la matriz de pesos W alternativa, la cual permite calular pesos en base a los residuos de la iteracion anterios (esto se usa en el robust_fit cuando t > 0)
def armarW2(residual):
    s = np.median(np.abs(residual))
    return np.diag(np.apply_along_axis(B, 0, [residual/(6*s)])[0])

# Funcion para armar la matriz A, cuyas columnas son los polynomial features de X
def armarA(X, grado):
    return PolynomialFeatures(grado).fit_transform(X)

# Usamos X e y para precedir el valor y_pred de x
def predict(X, y, x, q, grado, standardize=False, weight="W"):
    Xs = X.T.copy()
    X_std = np.ones(len(Xs))
    if standardize:
        for i in range(len(Xs)):
            X_std[i] = np.std(Xs[i])
            Xs[i] = (Xs[i])/np.std(Xs[i])
    Xs = Xs.T
    xs = x.copy()
    if standardize:
        xs = np.divide(xs, X_std)
    A = armarA(Xs, grado)
    W = armarW(Xs, xs, q, weight)
    theta = np.linalg.pinv(A.T @ W @ A) @ (A.T @ W @ y)
    return (theta.T @ armarA(xs.reshape(1, -1), grado).T)[0]

# Usamos X e y para fittear el modelo y conseguir y_fit, usamos predict para predecir todos los xi de X
def fit(X, y, q, grado, standardize=False, weight="W", t=0):
    if t > 0:  # si t > 0 usamos robust_fit
        return robust_fit(X, y, q, grado, standardize, weight, t)
    y_fit = []
    for x in X:
        y_fit.append(predict(X, y, x, q, grado, standardize, weight))
    return np.array(y_fit)

# Usamos X e y para fittear el modelo y conseguir y_fit, usamos A y cada Wi (son len(X) matrices W) para predecir todos los xi de X, si t > 0 volvemos a fittear con la matriz de pesos WB (armarW2), repetimos t veces
def robust_fit(X, y, q, grado, standardize=False, weight="W", t=2):
    B = np.ones((len(X), (len(X.T)**grado)+1))
    Xs = X.T.copy()
    if standardize:
            for i in range(len(Xs)):
                Xs[i] = (Xs[i])/np.std(Xs[i])
    Xs = Xs.T
    y_fit = np.zeros(len(X)).reshape(-1)
    A = armarA(Xs, grado)
    WB = np.identity(len(Xs))
    for t_i in range(t+1):
        residual = y - y_fit
        if not (t_i == 0):
            WB = armarW2(residual)
        for i,xi in enumerate(Xs):
            W = armarW(Xs, xi, q, weight)
            W = W @ WB
            theta = np.linalg.pinv(A.T @ W @ A) @ (A.T @ W @ y)
            y_fit[i] = (theta.T @ A.T)[i]
    return y_fit