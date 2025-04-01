FROM openjdk:21-slim

WORKDIR /app

COPY target/ServidorTarea-0.0.1-SNAPSHOT.jar ServidorTarea.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ServidorTarea.jar"]