# Este .py sirve para medir las metricas usando el paquete sklearn (metricas sobre los clasificadores de sklearn)
# La idea era comparar los clasificadores con los que hicimos nosotros
from sklearn.neighbors import KNeighborsClassifier as knn
import numpy as np
import pandas as pd
from sklearn.decomposition import PCA
from sklearn.model_selection import cross_val_score
from sklearn.utils import shuffle
import numpy as np
import matplotlib.pyplot as plt

df_train = pd.read_csv("../csv/train.csv", delimiter=',')

df_aMano = pd.read_csv("../csv/testAMano.csv", delimiter=',')

df_expected = pd.read_csv("../csv/expectedAMano.csv", delimiter=',')

def metricas(df, num_det, k=10, alpha=30, cv=5, proporcion=0.8):
    df = shuffle(df)

    X_all, Y_all = np.array(df.iloc[:, 1:]), np.array(df['label'])

    # lo convierto a int
    Y = Y_all.astype(int)
    X = X_all.astype(int)

    Y[Y_all == num_det] = 1
    Y[Y_all != num_det] = 0

    limit = int(len(df) * proporcion)

    X_train, Y_train = X[:limit], Y[:limit]
    X_test, Y_test = X[limit:], Y[limit:]

    print("# train = {}, # test = {} ".format( len(X_train), len(X_test))  )
    print(len(np.unique(Y_train)))
    print(np.unique(Y_train))
    print(np.unique(Y_test))

    pca = PCA(n_components=alpha).fit(X_train)
    Xtc_train = pca.transform(X_train)
    Xtc_test = pca.transform(X_test)

    print(X_train.shape, Xtc_train.shape, X_test.shape, Xtc_test.shape)

    clf = knn(n_neighbors=k)
    clf.fit(Xtc_train, Y_train);

    preds = clf.predict(Xtc_test)
    ok = preds == Y_test
    # preds.shape, Y_test.shape
    err = sum(ok) / len(ok)
    print(f"Deteccion del nro {num_det} con accuracy:", err)

    kfold_cv = cross_val_score(clf, Xtc_train, Y_train, cv=cv)

    print(kfold_cv)


def metricasAMano(X_train, Y_train, X_test, Y_test, k=10, alpha=30, cv=5):
    pca = PCA(n_components=alpha).fit(X_train)
    Xtc_train = pca.transform(X_train)
    Xtc_test = pca.transform(X_test)

    print(X_train.shape, Xtc_train.shape, X_test.shape, Xtc_test.shape)

    clf = knn(n_neighbors=k)
    clf.fit(Xtc_train, Y_train);

    preds = clf.predict(Xtc_test)
    ok = preds == Y_test
    # preds.shape, Y_test.shape
    err = sum(ok) / len(ok)
    print("Accuracy:", err)

    kfold_cv = cross_val_score(clf, Xtc_train, Y_train, cv=cv)

    print(kfold_cv)


def metricas_multiclase(df, k=10, alpha=30, cv=5, proporcion=0.8):
    df = shuffle(df)

    X_all, Y_all = np.array(df.iloc[:, 1:]), np.array(df['label'])

    # lo convierto a int
    Y = Y_all.astype(int)
    X = X_all.astype(int)

    limit = int(len(df) * proporcion)

    X_train, Y_train = X[:limit], Y[:limit]
    X_test, Y_test = X[limit:], Y[limit:]

    print("# train = {}, # test = {} ".format( len(X_train), len(X_test))  )
    print(len(np.unique(Y_train)))
    print(np.unique(Y_train))
    print(np.unique(Y_test))

    pca = PCA(n_components=alpha).fit(X_train)
    Xtc_train = pca.transform(X_train)
    Xtc_test = pca.transform(X_test)

    print(X_train.shape, Xtc_train.shape, X_test.shape, Xtc_test.shape)

    clf = knn(n_neighbors=k)
    clf.fit(Xtc_train, Y_train);

    preds = clf.predict(Xtc_test)
    ok = preds == Y_test
    # preds.shape, Y_test.shape
    err = sum(ok) / len(ok)
    print("Accuracy:", err)

    kfold_cv = cross_val_score(clf, Xtc_train, Y_train, cv=cv)

    print(kfold_cv)


# metricas(df_train, 4)
# metricas_multiclase(df_train)

X_train, Y_train = np.array(df_train.iloc[:, 1:]), np.array(df_train['label'])
X_test, Y_test = np.array(df_aMano), np.array(df_expected['Label'])

metricasAMano(X_train, Y_train, X_test, Y_test, k=5, alpha=43)