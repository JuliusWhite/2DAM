Imagen Docker

Imagen personalizada con libreria externa

Para crear una imagen usamos el fichero Dockerfile

Para construirla usamos el comando:

$ docker build -t youtubeimagen:latest .

Antes tenemos que descargar la imagen base, en nuestro caso la de python:3

$ docker pull python:3

Una vez creada la imagen podemos usarla para lanzar el contenedor

$ docker run --rm -it youtubeimagen
Dependencias

Usamos la instrucción pyreqs para crear el fichero requirements.txt. Este fichero contendrá todas las librerias que use nuestro script.

$ pipreqs --force

En el Dockerfile podemos incluir la instalación de las librerias:

COPY requirements.txt ./ RUN pip install --no-cache-dir -r requirements.txt