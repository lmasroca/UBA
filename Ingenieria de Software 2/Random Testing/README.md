# Resumen

Se experimento con mutation analysis y random testing en Java. Se implemento un invariante de representacion para filtrar casos de test generados aletoriamente, se uso random testing para detectar problemas en el programa, se creo un test suite para maximizar el mutation score y se detectaron mutantes equivalentes.

# Herramienta Randoop

Randoop es una herramienta de generación automática de casos de test unitarios para Java (en formato JUnit). Esta herramienta implementa una técnica de generación de casos de tests aleatorios, guíados por retroalimentación ([feedback-directed random testing](https://homes.cs.washington.edu/~mernst/pubs/feedback-testgen-icse2007.pdf)). Cada caso de test consiste en una secuencia de llamadas a métodos de la clase bajo prueba, seguida de una aserción que captura el comportamiento esperado de la clase bajo prueba. Randoop puede ser utilizado con dos propósitos: encontrar errores en un programa y crear tests de regresión para detectar si cambia el comportamiento de su programa en el futuro.
Se utilizó Randoop para generar automáticamente casos de test unitarios para la clase StackAr. Los siguientes links proveen más información al respecto de esta herramienta:

- [Website de Randoop](https://randoop.github.io/randoop/)
- [Manual de uso de Randoop](https://randoop.github.io/randoop/manual/index.html)


# Herramienta Pitest

Pitest es una herramienta para generar mutantes de un programa Java y calcular el mutation score de un test suite. Se utilizó Pitest para generar automáticamente mutantes de la clase StackAr y calcular el mutation score de un test suite generado por Randoop. Los siguientes links proveen más información al respecto de esta herramienta:

- [Operadores de mutación disponibles en Pitest](https://pitest.org/quickstart/mutators/)
- [Cómo utilizar Pitest por línea de comandos](https://pitest.org/quickstart/commandline/)
- [Cómo utilizar Pitest con el plugin de Gradle](https://gradle-pitest-plugin.solidsoft.info/)


# Setup

Importar el proyecto StackAr en la IDE IntelliJ IDEA. Este proyecto contiene una implementación de un stack (pila) en Java.
Para generar los tests unitarios con Randoop, ejecutar la Gradle task randoop. Esto puede hacerlo desde la IDE abriendo el panel Gradle y haciendo doble-click sobre la tarea randoop (en la sección de verification). Los archivos generados por Randoop se encuentran en la carpeta src/test/java del proyecto, con el nombre RegressionTest#.java. Tenga en cuenta que cada archivo puede contener más de un test. Para inspeccionar la configuración de la herramienta Randoop, puede revisar los argumentos de la Gradle task randoop en el archivo build.gradle.
Una vez generados, puede correr los tests unitarios desde la IDE haciendo click derecho sobre el módulo test y seleccionando Run Tests. El reporte de coverage generado por JaCoCo al finalizar los tests se encuentra en el archivo build/reports/jacoco/test/html/index.html.
Para correr Pitest, ejecutar la Gradle task pitest. Esto puede hacerlo desde la IDE abriendo el panel Gradle y haciendo doble-click sobre la tarea pitest (en la sección de verification). El output del report de Pitest queda en build/reports/pitest/index.html. Para inspeccionar la configuración de la herramienta Pitest, puede revisar los argumentos de la Gradle task pitest en el archivo build.gradle.
Dado que Pitest trabaja a nivel de bytecode, vamos a utilizar la herramienta Java Decompiler para convertir el bytecode generado por Pitest a código fuente Java, y poder inspeccionar los mutantes generados. Esto ya se encuentra configurado en el archivo build.gradle. Luego de correr Pitest, puede encontrar los mutantes generados en la carpeta build/reports/pitest/export/org/autotest/StackAr/mutants.