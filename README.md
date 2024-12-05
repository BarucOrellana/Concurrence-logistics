# Simulador de Asignación de Unidades en Transporte de Mercancías 🚛

Este proyecto es un simulador desarrollado en **Java** que demuestra la aplicación práctica de la **programación concurrente** para resolver un problema común en la logística de transporte: la asignación eficiente de recursos limitados, como unidades de transporte (camiones), entre múltiples operadores (conductores).

La simulación aborda un escenario en el que el número de operadores supera al de unidades disponibles, lo que requiere una gestión coordinada para garantizar que ningún recurso sea utilizado simultáneamente por más de un operador y que todos los operadores puedan trabajar de forma equilibrada.

## Motivación y objetivo

La idea surge de la necesidad de resolver problemas reales en la logística de transporte de mercancías, donde la gestión eficiente de recursos es clave para maximizar la productividad y minimizar tiempos muertos. Además, busca ser una introducción práctica a los conceptos de programación concurrente, mostrando cómo las herramientas ofrecidas por **Java**, como hilos, semáforos y colas bloqueantes, pueden utilizarse para coordinar tareas en sistemas multitarea.

## Componentes principales del simulador

### Unidades (camiones):
- Representan los recursos físicos que los operadores utilizan para realizar sus tareas.
- Cada unidad está protegida por un mecanismo de bloqueo (**ReentrantLock**) para evitar conflictos de acceso.

### Operadores (conductores):
- Representan hilos en la simulación, cada uno encargado de solicitar una unidad, realizar un proceso de "conducción" y luego liberar la unidad para que otros puedan utilizarla.
- Incorporan pausas entre tareas para simular descansos, añadiendo realismo al modelo.

### Patio (Yard):
- Es el núcleo de la simulación, encargado de crear y gestionar tanto a los operadores como a las unidades.
- Utiliza un **ExecutorService** para ejecutar los hilos de los operadores de forma concurrente y ordenada.

## Funcionamiento

### Inicialización:
- El usuario especifica la cantidad de operadores y unidades.
- El sistema valida que haya al menos tantas unidades como operadores para evitar inconsistencias.

### Asignación de unidades:
- Los operadores solicitan unidades mediante una cola de tareas.
- Una vez asignada, la unidad queda bloqueada para que otros operadores no puedan usarla hasta que sea liberada.

### Simulación de conducción:
- Cada operador conduce su unidad durante un tiempo definido (5 segundos), simulado mediante un retraso.
- Tras completar la tarea, el operador libera la unidad y toma un tiempo de descanso antes de volver a solicitar otra.

## Aspectos técnicos destacados

- **Gestión de concurrencia:** Uso de semáforos (**Semaphore**) para limitar el acceso simultáneo y garantizar la integridad de los recursos compartidos.
- **Sincronización de hilos:** Implementación de bloqueos (**ReentrantLock**) para evitar condiciones de carrera y garantizar que cada unidad sea utilizada por un solo operador a la vez.
- **Eficiencia:** Uso de un **ExecutorService** para gestionar un grupo fijo de hilos, optimizando el uso de recursos del sistema.
- **Aleatoriedad controlada:** Asignación aleatoria de unidades a operadores, pero siempre respetando las reglas de exclusividad.

## Resultados

En la simulación, se observa cómo:
- Las unidades son asignadas y liberadas de forma ordenada, evitando conflictos.
- Los operadores trabajan de manera cíclica, asegurando que todos tengan la oportunidad de utilizar una unidad.
- Se respetan los tiempos de conducción y descanso, añadiendo realismo al modelo.

## Relevancia

Este proyecto no solo es una introducción práctica a la programación concurrente, sino que también tiene aplicaciones directas en el mundo real, como en:
- **Gestión logística:** Optimización de recursos en almacenes o flotas de transporte.
- **Simulaciones de sistemas:** Modelado de sistemas multitarea en industrias como la manufactura o el transporte público.
- **Desarrollo de software escalable:** Creación de aplicaciones que necesitan manejar múltiples usuarios o procesos simultáneamente.

## Conclusión

Este simulador muestra cómo problemas complejos pueden abordarse de manera eficiente utilizando técnicas de programación concurrente. Es una herramienta educativa y práctica que destaca el poder de Java para desarrollar soluciones robustas y escalables.
