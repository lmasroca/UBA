def csv_to_df(f_import, f_export):
    """
    Construye a partir del csv f_import otro csv con el nombre de f_export
    para tener los parametros y las metricas con un formato que pueda ser leido 
    por el paquete de pandas como dataframe 
    """
    text = []
    with open(f"../csv/{f_import}") as f:
        for line in f.readlines():
            l = line.split(' ')
            if l[0] == "Parametros":
                new_line = ""
                for p in l[1:-1]:
                    new_line += p + ","
                new_line += l[-1].split('\n')[0]
            elif l[0] == "Duracion:":
                new_line += "," + (l[1].split('s'))[0]
            elif l[0] == "Accuracy":
                new_line += "," + (l[2].split(','))[0]
                new_line += "," + l[5]
                text.append(new_line)
        f.close()

    with open(f"../csv/{f_export}", 'w') as f:
        f.write("pca,k,vot_pes,alpha,epsilon,duracion,acc,std\n")
        for line in text:
            f.write(line)
        f.close()

    print("listo!")

def metricas_to_df(n_linea, f_import, f_export):
    """
    Construye un csv que contenga el mismo formato de pandas para df
    pero solo para la tabla de metricas de un fold en especfico
    """
    text = []
    num = 0
    with open(f"../csv/{f_import}") as f:
        for line in f.readlines()[n_linea:n_linea+10]:
            l = line.split(' ')
            text.append(str(num) + "," + l[4] + "," + l[7] + "," + l[10] + "," + l[13])
            num += 1
        f.close()

    with open(f"../csv/{f_export}", 'w') as f:
        f.write("numero,accuracy,precision,recall,f1-score\n")
        for line in text:
            f.write(line)
        f.close()

    print("listo!")

# contruccion de 5-fold_KyAlpha 
# csv_to_df("5-fold_exp.csv", "5-fold_KyAlpha.csv")

# experimento kNN con votpesada y no pesada variando los k 
# csv_to_df("4-foldCV_resultados.csv", "4-fold_VotPes_kNN.csv")

# construccion del 3-fold_result en formato df
# csv_to_df("3-foldCV_resultados.csv", "3-fold_Alphas.csv")

# construccion de tabla de metricas para 5foldexp
# metricas_to_df(12362, "5-fold_exp.csv", "metricas_5foldexp.csv")

# construccion de tabla de metricas para 3foldexp
# metricas_to_df(482, "3-foldCV_resultados.csv", "metricas_3foldexp.csv")

# construccion de segunda tabla del experimento de epsilon
# metricas_to_df(346, "epsilon.csv", "metricas_3foldexp.csv")