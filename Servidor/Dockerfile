FROM openjdk:21-slim

WORKDIR /app

COPY target/Servidor-0.0.1-SNAPSHOT.jar Servidor.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Servidor.jar"]