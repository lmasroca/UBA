import numpy as np
import pandas as pd
import subprocess
from sklearn.utils import shuffle
import matplotlib.pyplot as plt
from tqdm.auto import tqdm

df_train = pd.read_csv("../csv/train.csv", delimiter=',')
df_shuffled = shuffle(df_train)

def metricas(i, preds, Y_test):
    tp = np.sum( np.logical_and(preds == i, Y_test == i) )
    tn = np.sum( np.logical_and(preds != i, Y_test != i) )
    fp = np.sum( np.logical_and(preds == i, Y_test != i) )
    fn = np.sum( np.logical_and(preds != i, Y_test == i) )
    acc = (tp + tn) / (tp+tn+fp+fn)
    if (tp + fp) == 0:
        prec = 0
    else:
        prec = tp / (tp + fp)
    if (tp + fn) == 0:
        rec = 0
    else:
        rec = tp / (tp + fn)
    f1_score = 2 * (prec * rec) / (prec + rec)
    return acc, prec, rec, f1_score

def kFold_CV(df, cv=5, PCAbool="1", k="5", votPes="1", alpha="50", epsilon="1e-6", guardarKMasCercanosBool="0", guardarAvectsBool="1", leerKMasCercanos="0", leerAVect="0"):
    """
    Esta función permite caluclar kNN y kNN+PCA con el método de K-Fold Cross Validation.
    Además del retorno, se creará un csv llamado "N-foldCV_resultados.csv", siendo N el
    número de folds que calculará el algoritmo, en el cual se guardarán todos los parametros 
    utilizados, la duración de cada fold, las métricas de cada clase, el accuracy promedio, 
    el desvío estándar, entre otros. 

    Parametros:
        - df: DataFrame que contenga la base de Train con sus labels y pixeles correspondientes
        - cv: Número de folds
        - PCAbool: '1' si queremos PCA, '0' si no.
        - votPes: Analogo al anterior
        - alpha: Número de alpha para PCA
        - epsilon: Número de epsilon para convergencia
        los demás son bastante auto-explicativos

    Return:
        [0] Lista de Accuracy multiclase para cada fold
        [1] Tiempo total de cómputo entre todos los folds
        [2] Diccionario de métricas para cada fold  
        [3] Lista de DataFrames con el expected y el prediction de la base de test cada fold  
    """

    #Nos fijamos que la base pueda dividirse en partes iguales 
    if (df.shape[0] % cv != 0):
        return f"No se puede dividir esta base de datos en {cv} partes iguales enteras"

    duracion = 0
    accuracy = []
    metricas_tot_kfolds = {}
    list_df_preds = []

    with open(f"../csv/{cv}-foldCV_resultados.csv", 'a') as f:
        f.write("------------------------------------------------------------------------------------------------\n")
        f.write("------------------------------------------------------------------------------------------------\n")
        f.write("Parametros" + ' ' + PCAbool + ' ' + k + ' ' + votPes + ' ' + alpha + ' ' + epsilon + '\n')
        f.close()

    folds = range(1, cv+1)

    for fold in tqdm(folds):
        # output.csv será un sv temporall para guardar los resultados del tp2.cpp
        outCsv = "output.csv"
        comando = ["../cpp/tp2", PCAbool, k, votPes, alpha, epsilon, "partTrain.csv", "partTest.csv", outCsv, guardarKMasCercanosBool, guardarAvectsBool, leerKMasCercanos, leerAVect, str(fold-1)]

        #Particionamos la base en partes iguales
        limite = (int((len(df) / cv) * (fold - 1)), int((len(df) / cv) * fold))
        X_train = pd.concat([df.iloc[:limite[0]], df.iloc[limite[1]:]])
        X_test = df.iloc[limite[0]:limite[1]]
        X_train.to_csv("../csv/partTrain.csv", index = False)
        X_test.to_csv("../csv/partTest.csv", index = False, columns = df.columns[1:])

        # Calculamos llamando al tp2.cpp
        result = subprocess.run(comando, stdout=subprocess.PIPE, encoding='ascii')
        duracion += int(result.stdout.split(' ')[-2])

        pred = pd.read_csv("../csv/" + outCsv, delimiter=',')

        pred_labels = np.array(pred['Label'])
        test_labels = np.array(X_test['label'])

        # Agregamos las predicciones a la lista de dfs
        array_preds = np.array([pred_labels, test_labels]).T
        list_df_preds.append(pd.DataFrame(array_preds, columns=["pred", "test"]))

        # Agregamos el accuracy multiclase del fold 
        ok = pred_labels == test_labels
        accuracy.append(sum(ok) / len(ok)) 

        metricas_totles = []
        # Escribimos en el csv la tabla de metricas de cada clase para cada fold
        with open(f"../csv/{cv}-foldCV_resultados.csv", 'a') as f:
            f.write("Fold numero " + str(fold) +  ", Duracion: " + result.stdout.split(' ')[-2] + "seg \n")
            for i in range(10):
                metricas_i = metricas(i, pred_labels, test_labels)
                metricas_totles.append(metricas_i)
                f.write("Numero " + str(i) + ": Acc = " + str(metricas_i[0]) + " Prec = " + str(metricas_i[1]) + " Rec = " + str(metricas_i[2]) + " F1-Score = " + str(metricas_i[3]) + "\n")
            f.close()
        # Agregamos las metricas al diccionario
        metricas_tot_kfolds[f"fold{fold}"] = metricas_totles

    # Escribimos en el csv otras métricas generales de nuestro fold
    with open(f"../csv/{cv}-foldCV_resultados.csv", 'a') as f:
            f.write("Duracion: " + str(duracion) + "seg, " + "Accuracy scores: " + str(accuracy) + "\n")
            f.write("Accuracy promedio: " + str(np.mean(accuracy)) + ", Desviacion Estandar: " + str(np.std(accuracy)) + "\n")
            f.close()

    print("Listo!")

    return duracion, accuracy, metricas_tot_kfolds, list_df_preds

def kappaDeCohen(df_c1, df_c2): 
    """
    Esta funcion calcula el índice de kappa de Cohen para bases de labels Y_test, 
    que vendrian a ser los labels esperados, con los y_pred, que vienen a ser los
    labels estimados. 

    Parametros: Ambos parametros son listas de dataframes con expected y prediction,
    por cada fold realizado con la funcion kFold_CV. Estos dataframes pueden ser los 
    mismos sacados del ultimo valor del return de kFold_CV.

    Return: Devuelve una lista de índices por cada fold.
    """

    kappa_list = []

    for i in range(len(df_c1)):
        ok_c1 = df_c1[i]['test'] == df_c1[i]['pred']
        ok_c2 = df_c2[i]['test'] == df_c2[i]['pred']
        ok_c1yc2 = ok_c1 & ok_c2
        nok_c1yc2 = (ok_c1 == False) & (ok_c2 == False)

        print(pd.DataFrame(np.transpose([ok_c1, ok_c2, ok_c1yc2, nok_c1yc2])))

        pr_a = (sum(ok_c1yc2) + sum(nok_c1yc2)) / len(df_c1[i])
        print(pr_a)

        pr_e = (ok_c1.mean() * ok_c2.mean()) + ((1 - ok_c1.mean()) * (1 - ok_c2.mean()))
        print(pr_e)

        kappa = (pr_a - pr_e) / (1 - pr_e) 
        kappa_list.append(kappa)

    return kappa_list

def kNN(df, PCAbool="1", k="5", votPes="1", alpha="50", epsilon="1e-6", proporcion=0.6):
    """
    Es igual al kFold_CV pero sin el metodo de k-Fold Cross Validation, por lo que se puede 
    especificar la proporcion de particion que queremos hacer para obtener nuestra base de 
    train y test. 
    El ultimo df_preds es un solo dataframe que se devuelve como una lista para poder usarla
    con la funcion kappaDeCohen.
    """

    with open(f"../csv/kNN_resultados.csv", 'a') as f:
        f.write("------------------------------------------------------------------------------------------------\n")
        f.write("------------------------------------------------------------------------------------------------\n")
        f.write("Parametros" + ' ' + PCAbool + ' ' + k + ' ' + votPes + ' ' + alpha + ' ' + epsilon + '\n')
        f.close()

    comando = ["../cpp/tp2", PCAbool, k, votPes, alpha, epsilon]

    limite = int(df_shuffled.shape[0] * proporcion)

    X_train = df.iloc[:limite]
    X_test = df.iloc[limite:]
    X_train.to_csv("../csv/partTrain.csv", index = False)
    X_test.to_csv("../csv/partTest.csv", index = False, columns = df.columns[1:])

    comando.append("partTrain.csv")
    comando.append("partTest.csv")

    outCsv = "output.csv"
    comando.append(outCsv)

    print(comando)

    result = subprocess.run(comando, stdout=subprocess.PIPE, encoding='ascii')
    print(result.stdout.split('\n')[0])
    if (len(result.stdout.split(' ')) > 7):
        print(epsilon, "no conv...")
    duracion = result.stdout.split(' ')[np.size(result.stdout.split(' '))-2]

    pred = pd.read_csv("../csv/" + outCsv, delimiter=',')

    pred_labels = np.array(pred['Label'])
    test_labels = np.array(X_test['label'])

    array_preds = np.array([pred_labels, test_labels]).T
    df_preds = pd.DataFrame(array_preds, columns=["pred", "test"])

    ok = pred_labels == test_labels
    acc = sum(ok) / len(ok)
    print("accuracy", acc)

    metricas_totales = []
    with open(f"../csv/kNN_resultados.csv", 'a') as f:
        f.write("Duracion: " + duracion + "seg" + ", Accuracy: " + str(acc) + "\n")
        for i in range(10):
            metricas_i = metricas(i, pred_labels, test_labels)
            f.write("Numero " + str(i) + ": Acc = " + str(metricas_i[0]) + " Prec = " + str(metricas_i[1]) + " Rec = " + str(metricas_i[2]) + " F1-Score = " + str(metricas_i[3]) + "\n")
        f.close()

    return int(duracion), acc, metricas_totales, [df_preds]