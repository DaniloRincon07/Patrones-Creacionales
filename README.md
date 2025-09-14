Sistema de Gestión de Inventarios con Patrones Creacionales
Este repositorio contiene un proyecto de gestión de inventarios desarrollado en Java, enfocado en la aplicación de patrones de diseño creacionales para construir una solución modular, robusta y fácil de mantener.

🚀 Patrones de Diseño Utilizados
El proyecto implementa los siguientes patrones creacionales para resolver los requisitos del negocio:

Factory Method: Se encarga de la creación de productos, permitiendo que la lógica de instanciación sea flexible y extensible a nuevas categorías.

Prototype: Utilizado para la clonación eficiente de objetos de productos ya existentes, agilizando el proceso de registro de inventario.

Singleton: Garantiza que exista una única instancia del gestor de inventario en toda la aplicación, centralizando el control y evitando inconsistencias en los datos.

🛠️ Configuración y Ejecución
Para compilar y ejecutar este proyecto, asegúrate de tener el JDK (Java Development Kit) instalado en tu sistema.

Clonar el Repositorio:
Abre tu terminal y clona el proyecto de GitHub.

git clone [https://github.com/DaniloRincon07/Patrones-Creacionales.git](https://github.com/DaniloRincon07/Patrones-Creacionales.git)

Navegar a la Carpeta del Proyecto:

cd Patrones-Creacionales

Compilar el Código:
Compila los archivos Java. Asegúrate de que todos los archivos (Main.java, Product.java, Category.java, etc.) estén en la misma carpeta.

javac Main.java

Ejecutar la Aplicación:
Ejecuta el archivo principal para ver el flujo completo de la aplicación y las pruebas en la consola.

java Main

📂 Estructura del Proyecto
El proyecto está organizado en las siguientes clases, cada una con un rol específico en la implementación de los patrones:

Main.java: La clase principal que orquesta el flujo de la aplicación y ejecuta las pruebas.

Category.java: Define la estructura para las categorías de productos.

Product.java: Implementa el patrón Prototype para la clonación de objetos.

ProductFactory.java: Define la interfaz para el patrón Factory Method.

ConcreteProductFactory.java: Implementa la lógica de la fábrica para crear productos.

InventoryManager.java: Implementa el patrón Singleton para la gestión centralizada del inventario.

👥 Autores
Andres Danilo Rincon Castro

Juan Esteban Vargas Botero

Juan Esteban Camargo Infante
