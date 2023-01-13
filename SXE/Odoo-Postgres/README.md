## Servidor Odoo con PostgreSQL, levantado con docker.

Primero se instala el postgresSQL en el equipo en caso de ser necesario. Una vez instalado, se comprueba el funcionamiento de ls servicios de postgresSQL con el siguiente comando: **sudo netstat -putan | grep post**.

![Screenshot from 2023-01-13 12-32-29](https://user-images.githubusercontent.com/91659331/212311657-15da7a77-64fb-45fd-a138-ad0fa95bd358.png)

Se genera un nuevo directorio de trabajo y se crea dentro el archivo 'docker-compose.yml', el cual levantara el nyuevo servidor Odoo.

Este archivo consta de dos servicios, uno web (Odoo) y una base de datos (PostgreSQL). Primero se genera la DB, indicando todos los parámetros necesarios y después se genera el servicio web, con Odoo, enlazándolo con la DB creada anteriormente, indicando en el servivio web que depende de la database que acabamos de crear.

Al ejecutar el comando para levantar los servicios, se generarán el la carpeta raíz unos directorios nuevos, autogenerados por el propio servicio.

Si se desea comrpobar que el sevidor funciona en local podemos entrar desde cualquier navegador al sitio web: **http://localhost:8069**, siendo el puerto 8069, el puerto indicado en el archivo docker.

Una vez ingreados los datos necesarios la web debería ser similar a la siguiente:

![Screenshot from 2023-01-13 11-31-42](https://user-images.githubusercontent.com/91659331/212299189-ca88b280-0caf-4601-9c2f-6a1d1177d072.png)