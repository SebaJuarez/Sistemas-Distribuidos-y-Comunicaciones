#!/bin/bash

echo " Compilando el proyecto con Maven..."
mvn clean package

echo " Construyendo la imagen Docker..."
docker build -t servidor .

echo " Eliminando contenedor anterior..."
docker rm -f servidor 2>/dev/null

echo " Levantando el contenedor..."
docker run -d --name servidor -p 8080:9090 servidor


# Hacer la solicitud HTTP POST y capturar la respuesta
echo -e "\n Enviando solicitud POST a /tarea..."
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "operador": "*",
          "numero1": "443",
          "numero2": "-4"
         }')

# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

echo -e "\n Proceso completado."