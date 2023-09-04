# Proyecto de Estructura de Datos - Implementación de IDoubleNotOrderedList

Este es el tercer proyecto de la asignatura de Estructura de Datos, centrado en la implementación del interface `IDoubleNotOrderedList` mediante dos estructuras de datos diferentes.

## Estructuras de Datos

### Lista Doblemente Enlazada

- Definida por referencias `front` y `last`.
- Referencia `prev` del primer nodo apunta a null.
- Referencia `next` del último nodo apunta a null.

### Lista Doblemente Enlazada Circular con Nodo Cabecera

- Definida por referencia `header` a un nodo vacío.
- El constructor crea un nodo doble vacío.

## Componentes del Proyecto

- Interface `IDoubleNotOrderedList` (no modificable).
- Clase `DoubleLinkedList.java` que implementa la lista doblemente enlazada.
- Clase `CircularDoubleWithHeaderList.java` que implementa la lista circular doblemente enlazada con nodo cabecera.
- Pruebas JUnit para ambas clases para garantizar su correcto funcionamiento.

