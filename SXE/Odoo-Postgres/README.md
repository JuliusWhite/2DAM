## Servidor Odoo con PostgreSQL, levantado con docker.

Se genera un nuevo directorio de trabajo y se crea dentro el archivo 'docker-compose.yml', el cual levantara el nyuevo servidor Odoo.

Este archivo consta de dos servicios, uno web (Odoo) y una base de datos (PostgreSQL). Primero se genera la DB, indicando todos los parámetros necesarios y después se genera el servicio web, con Odoo, enlazándolo con la DB creada anteriormente, indicando en el servivio web que depende de la database que acabamos de crear.

Al ejecutar el comando para levantar los servicios, se generarán el la carpeta raíz unos directorios nuevos, autogenerados por el propio servicio.

Si se desea comrpobar que el sevidor funciona en local podemos entrar desde cualquier navegador al sitio web: **http://localhost:8069**, siendo el puerto 8069, el puerto indicado en el archivo docker.