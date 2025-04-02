#!/bin/sh

#Iniciar el daemon de Docker en segundo plano
dockerd &

#Esperar a que Docker esté listo
while ! docker info >/dev/null 2>&1; do
  echo "Esperando a que Docker inicie..."
  sleep 1
done

echo "Docker está listo. Iniciando la aplicación Java..."

#Ejecutar la aplicación Java
exec java -jar /app/Servidor.jar