## Servidor Odoo con PostgreSQL, levantado con docker.

Primero se instala el postgresSQL en el equipo en caso de ser necesario. Una vez instalado, se comprueba el funcionamiento de ls servicios de postgresSQL con el siguiente comando: `sudo netstat -putan | grep post`.

![Screenshot from 2023-01-13 12-32-29](https://user-images.githubusercontent.com/91659331/212311657-15da7a77-64fb-45fd-a138-ad0fa95bd358.png)

En caso de que sea necesario. se ejecuta `service postgresql stop` para parar los servicios activos de postgres.

Se genera un nuevo directorio de trabajo y se crea dentro el archivo 'docker-compose.yml', el cual levantara el nyuevo servidor Odoo.

Este archivo consta de dos servicios, uno web (Odoo) y una base de datos (PostgreSQL). Primero se genera la DB, indicando todos los parámetros necesarios y después se genera el servicio web, con Odoo, enlazándolo con la DB creada anteriormente, indicando en el servivio web que depende de la database que acabamos de crear.

Al ejecutar el comando para levantar los servicios, se generarán el la carpeta raíz unos directorios nuevos, autogenerados por el propio servicio.

Si se desea comrpobar que el sevidor funciona en local podemos entrar desde cualquier navegador al sitio web: **http://localhost:8069**, siendo el puerto 8069, el puerto indicado en el archivo docker.

Una vez ingreados los datos necesarios la web debería ser similar a la siguiente:

![Screenshot from 2023-01-13 11-31-42](https://user-images.githubusercontent.com/91659331/212299189-ca88b280-0caf-4601-9c2f-6a1d1177d072.png)

Para sincronizar la DB con el PyCharm, será necesaria la version PyCharm professional, ya que es la única que permite la pestaña añadida de "databases". Se selecciona el añadir database y se selecciona postgres.

Se desplegará una ventana como la siguiente, donde se deben rellenar los datos necesarios para establecer la conexion con la DB.

![imagen](https://user-images.githubusercontent.com/91659331/212314086-829186f9-fd3c-42fa-8e44-41a28ff8469c.png)

Si da algún error debemos parar los servicios de postgresql en la terminal de sistema operativo y volver a ejecutar el `docker-compose up`. En caso contrario deberemos ver lo siguiente:

![Screenshot from 2023-01-13 12-58-52](https://user-images.githubusercontent.com/91659331/212316417-b28a8d08-0816-4d18-99b0-9aab09ba5bc7.png)

Para mostrar la db que se acaba de crear en PyCharm, se accede al menú de configuración de la db postgresql, se accede a 'Schemas' y se selecciona odoo_dam, para que se muestre en la pastaña 'Databases de PyCharm'.

![imagen](https://user-images.githubusercontent.com/91659331/212899135-b519047f-951d-4041-8f1c-1a76dc8f24ed.png)

![imagen](https://user-images.githubusercontent.com/91659331/212899325-0e49dad8-663b-4d81-b9fc-34d73bc093b9.png)

