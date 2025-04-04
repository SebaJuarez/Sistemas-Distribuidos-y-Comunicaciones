#!/bin/bash

echo " Compilando el proyecto con Maven..."
mvn clean package

echo " Construyendo la imagen Docker..."
docker build -t servidor .

echo " Eliminando contenedor anterior..."
docker rm -f servidor 2>/dev/null

echo " Levantando el contenedor..."
docker run --privileged -d --name servidor -p 8080:9090 servidor

sleep 25

echo -e "\n Proceso completado, ejecute pruebas.sh para enviar solicitudes."