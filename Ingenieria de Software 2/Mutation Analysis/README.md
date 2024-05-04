# Resumen

La técnica de *Mutation Analysis* permite juzgar la efectividad de un *test suite* midiendo cuan bien puede encontrar defectos “artificiales”. En esta técnica, un **mutante** es una versión levemente modificada del programa original que está bajo *test*. Decimos que un mutante está **vivo** si ningún test encuentra el defecto que introduce. Por otro lado, un mutante está **muerto** si al menos un test falla (i.e., detecta el defecto que introduce). Luego, para calcular el **mutation score** de un *test suite* tenemos que:

- Generar los mutantes del programa original.
- Para cada mutante, ejecutar todos los tests del *test suite* sobre el mutante.
- El **mutation score** es la cantidad de mutantes muertos dividida la cantidad total de mutantes. Por ejemplo, si tenemos 100 mutantes y 80 están muertos, el **mutation score** es 80 %.

Se implementa esta técnica en Java, usando el framework [Spoon](https://spoon.gforge.inria.fr/) para generar los mutantes usando algunos de los operadores de mutación definidos en la herramienta [PiTest](https://pitest.org/quickstart/mutators). Se maximizó el **mutation score** en un *test suite* y se analizó la existencia de **mutantes equivalentes**: mutantes indetectables debido a que no alteran el funcionamiento del código.

# Setup

Importar el proyecto en la IDE IntelliJ IDEA. Este proyecto contiene una implementación de un *stack* (pila) en Java, junto con varios *test suites* que lo prueban.
Además, contiene clases para generar mutantes del programa original, y para calcular el mutation score de un *test suite*. En detalle:

- El paquete `org.autotest.mutants` contendrá los mutantes del programa original (arranca vacío).
- El paquete `org.autotest.operators` contiene los operadores de mutación que se pueden aplicar.
- Las clases `Mutant` y `MutantsGenerator` se encargan de generar los mutantes del programa original.
- La clase `Main` es la que se debe ejecutar para generar los mutantes del programa original.
- La clase `StackAr` es la implementación original del stack.
- La interfaz `Stack` es la interfaz que implementa `StackAr`, y todos los mutantes que generemos.
- La clase abstracta `MutationAnalysisRunner` (en la carpeta */test*).

Para generar los mutantes del programa original debemos correr la clase `Main` del proyecto. Esto se puede hacer desde la IDE haciendo click derecho sobre la clase y seleccionando *Run Main.main()*. Los mutantes generados se encuentran en la carpeta `src/main/java/org/autotest/mutants` del proyecto. Cada mutante contará con un comentario *JavaDoc* al principio que indica el operador de mutación que se aplicó.
Para correr los tests, hacer click derecho sobre el módulo test en la IDE y seleccionar *Run Tests*. El reporte de *coverage* generado por `JaCoCo` al finalizar los tests se encuentra en el archivo `build/reports/jacoco/test/html/index.html`.