# Resultados


## Maximo mutation score

95% de mutation score, obtenido por el test suite 3.

## Mutantes vivos

Los siguientes mutantes son los que aún se encuentran vivos, se muestra a continuación porque estos mutantes son equivalentes y, por lo tanto, no se pueden matar:

- Reemplazar isEmpty() por false en la línea 45 (pop): Reemplazar el checkeo de si está vacío el stack en el pop no afecta el resultado, ya que luego llama a top que hace el mismo checkeo.
- Reemplazar this==obj por false en la línea 72 (equals): Es equivalente, ya que después se checkea que el index y los elementos sean iguales, lo cual será cierto si eran el mismo objeto.
- Reemplazar * por / en la línea 65 (hachCode): es equivalente porque termina multiplicando y dividiendo por 1 y eso da lo mismo.