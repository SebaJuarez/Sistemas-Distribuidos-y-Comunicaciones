#Usa Alpine como base con OpenJDK 21
FROM alpine:latest

#Instalar dependencias necesarias para Docker y OpenJDK 21
RUN apk add --no-cache \
    openjdk21 \
    docker \
    && rm -rf /var/cache/apk/*

#Establecer el directorio de trabajo
WORKDIR /app

#Copiar el archivo JAR de la aplicación
COPY target/Servidor-0.0.1-SNAPSHOT.jar Servidor.jar

#Exponer el puerto de la aplicación
EXPOSE 8080

#Copiar un script de inicio para arrancar Docker automáticamente
COPY start.sh /start.sh
RUN chmod +x /start.sh

#Ejecutar el script al iniciar el contenedor
CMD ["/start.sh"]