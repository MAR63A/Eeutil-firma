 # Docker

Se añade un fichero **Dockerfile** para construir el contenedor docker que permita la prueba y ejecución de la aplicación de forma autónoma e independiente del entorno de explotación de la CARM.

El único requisito que debe cumplir la máquina donde se pretenda ejecutar el proyecto **eeutil-firma** es que tenga instalada la aplicación [Docker](https://www.docker.com/)

## Generar imagen

Para el montaje solo es necesario construir una imagen docker a partir del fichero *Dockerfile*. Para ello sitúese en la carpeta ``/docker`` del proyecto y ejecute el siguiente comando:

```sh
$ sudo docker build -t eeutil-firma .
```

El comando ``build`` crea una imagen Docker a partir de un *Dockerfile*. La opción ``-t`` se usa para poner nombre a la imagen. 

Tras su ejecución se irán sucediendo una serie de pasos definidos en el *Dockerfile* que darán como resultado una imagen con el war de la aplicación y un servidor tomcat, listo para ejecutarse.

El Dockerfile se divide en 3 etapas:

* La primera utiliza una imagen git ``alpine/git`` para descargar ambos proyectos del repositorio
```docker 
FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/josefsabater/eeutil-shared.git
RUN git clone https://github.com/MAR63A/Eeutil-firma.git
```

* La segunda utiliza una imagen maven ``maven:3.6.3-jdk-8`` para compilar los proyectos
```docker 
FROM maven:3.6.3-jdk-8
WORKDIR /app
COPY --from=0 /app /app
WORKDIR /app/eeutil-shared
RUN mvn install
WORKDIR /app/Eeutil-firma
RUN mvn install
```

* La tercera y última utiliza una imagen tomcat de la versión 8.5 ``tomcat:8.5-jdk8`` para desplegar el war generado en la etapa anterior. Además, se le pasa al servidor los argumentos de entrada mediante la variable de entorno ``JAVA_OPTS``
```docker
FROM tomcat:8.5-jdk8
ENV JAVA_OPTS="-Djavax.net.ssl.trustStore=/usr/local/tomcat/conf/eeutil/truststore.jks \
-Djavax.net.ssl.trustStorePassword=changeit \
-Dlocal_home_app=/usr/local/tomcat/conf/eeutil \
-Deeutil-firma.config.path=/usr/local/tomcat/conf/eeutil"
WORKDIR /app
COPY --from=1 /app/Eeutil-firma/fuentes/eeutil-firma/target/eeutil-firma.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]
```

Tras finalizar el proceso, se habrá creado la imagen ``eeutil-firma`` en el repositorio local de Docker. Se puede consultar dicho repositorio ejecutando el comando:

```sh
$ sudo docker images
```

## Ejecutar contenedor

Una vez hemos generado la imagen con todo lo necesario, hay que ejecutar un contenedor que despliegue la aplicación partiendo de dicha imagen. Para ello debemos ejecutar el siguiente comando, sustituyendo los siguientes parámetros:

* ``<path_config>``: Ruta absoluta al directorio con los ficheros de configuración y al **truststore.jks**. Puede ser la ruta donde se encuentran en el proyecto ``<path>/resources/config`` *(recuerda que la ruta debe ser absoluta)* o cualquier otra. Es importante recordar que los ficheros de configuración que se encuentran en ``<path>/resources/config`` son en realidad una **plantilla** para la configuración, no tienen valores reales de conexión ni configuración. Además, el fichero **truststore.jks** tendrá que ser añadido explícitamente, ya que no se encuentra entre los ficheros de configuración
* ``<path_logs>``: Ruta absoluta al directorio donde se generarán los logs de la aplicación. Puede ser cualquiera de la máquina donde se va a ejecutar el contenedor

```sh
$ sudo docker run --name eeutil-firma-c -v <path_config>:/usr/local/tomcat/conf/eeutil -v <path_logs>:/usr/local/tomcat/logs -p 8080:8080 eeutil-firma
```

Breve resumen sobre cada parámetro de la ejecución:
* ``--name eeutil-firma-c``: Nombre del contenedor 
* ``-v <path_config>:/usr/local/tomcat/conf/eeutil``: Mapeo del directorio de configuración del host en el contenedor
* ``-v <path_logs>:/usr/local/tomcat/logs``: Mapeo del directorio de logs del host en el contenedor
* ``-p 8080:8080``: Mapeo del puerto 8080 del contenedor en el mismo puerto del host. Esto permite que la aplicación desplegada en el puerto 8080 del contenedor sea accesible desde el puerto 8080 del host

### Fichero de configuración
La ruta de donde se obtienen los ficheros de configuración, se establece como argumento del servidor Tomcat al arrancarlo, mediante la variable de entorno **JAVA_OPTS**. Estos parámetros establecen rutas propias del contenedor donde se ejecuta el servidor y a las que **no** se tiene acceso desde el exterior. Por poder pasárle al contenedor los ficheros de configuración desde el exterior utilizamos la opción -v, que permite mapear directorios entre un contenedor y el host donde se ejecuta.

Además, tambíen se ha establecido el directorio de configuración como ruta para obtener el fichero **truststore.jks**. Lo que significa que se debe añadir al directorio de los ficheros de configuración para que el servidor Tomcat lo pueda coger. 

### Logs
Notesé que entre los parámetros de entrada de despliegue del servidor, no se encuentra ninguno que indique la localización del log. Esto se debe a que dicha localización se encuentra definida en el fichero de configuración **log4j.properties**. Es por eso que, antes de ejecutar el contenedor, deberá modificar esta ruta para establecer la propia del contenedor:

```
log4j.appender.GENERAL.File=/usr/local/tomcat/logs/eeutil-firma.log
```

## Para terminar

Puede comprobar que el contenedor esta ejecutándose correctamente accediendo mediante un navegador a la ruta http://localhost:8080/Eeutil-firma/ws

Un último consejo para finalizar: Si desea utilizar el contenedor para realizar despliegues mientra desarrolla, tenga en cuenta que el comando ``docker build`` utiliza una cache propia para ahorrarse etapas. Esto significa que si sube algún cambio al repositorio y vuelve a ejecutar un ``docker build``, éste cambio **no se reflejará en el despliegue**, ya que no se descargará de nuevo el código. 

Para sortear este inconveniente, puede ejecutar el ``docker build`` con el parámetro ``--no-cache``, de esta forma se volverán a ejecutar todas las etapas:

```sh
$ sudo docker build -t eeutil-firma --no-cache .