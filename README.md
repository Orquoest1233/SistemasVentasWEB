# Sistemas de ventas web con servlets en java
Un sistema de gestión de pedidos implementado un mecanismo de sincronización basado en relojes mágicos de Lamport para asignar marcas de tiempo a las operaciones realizadas a los usuarios,
el uso de relojes para evitar inconsistencias con multiples clientes en simultaneo ademas de registro de pedidosm calculos de subtotales, IGV y el total en orden fijo y coherente dentro de un sistema distribuido
<img width="1359" height="691" alt="image" src="https://github.com/user-attachments/assets/ca0017ff-e4c9-465e-9519-3b38e2adfffa" />
## Características de la sincronización de los pedidos registrados en el sistema web
Para mantener el orden aplicamos sincronización de hilos mediante el uso del bloque synchronized en las partes críticas delcódigo. Esto evitara que dos solicitudes simultáneas modifiquen los mismos datos al mismo tiempo. Adicionalmente, se incluyen mecanismos de
validación usando try-catch, lo que permitecontrolar excepciones y asegurar que los datos sean correctos antes de procesarlos. Los cálculos de subtotal, IGV (18%) y total se realizan de manera automática y segura, garantizando que cada pedido llegue a la base de datos con la información completa y precisa.
<img width="1222" height="622" alt="image" src="https://github.com/user-attachments/assets/9fad3b4d-3c9f-45dc-b91e-38766ce66fb0" />

## Authors
- [@Orquoest](https://github.com/Orquoest)
- [@Orquoest](https://github.com/Orquoest1233)
