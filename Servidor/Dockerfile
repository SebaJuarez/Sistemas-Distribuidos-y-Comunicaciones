FROM openjdk:21-slim

WORKDIR /app

# Instalar Docker en la imagen
RUN apk add --no-cache \
    curl \
    bash \
    docker

COPY target/Servidor-0.0.1-SNAPSHOT.jar Servidor.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Servidor.jar"]