#!/bin/bash

# Hacer la solicitud HTTP POST y capturar la respuesta
echo -e "\n Enviando solicitudes POST a /tarea..."
echo -e "\n OPERACION_BASICA"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_BASICA"
          "operador": "*",
          "numero1": "443",
          "numero2": "-4"
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3

echo -e "\n OPERACION_COMPARACION"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_COMPARACION"
          "operador": ">=",
          "numero1": "54",
          "numero2": "43"
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3

echo -e "\n OPERACION_LOGICA"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_LOGICA"
          "operador": "XOR",
          "numero1": "true",
          "numero2": "false"
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3

echo -e "\n OPERACION_INEXISTENTE"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_INEXISTENTE"
          "operador": "23",
          "numero1": "4SAFD",
          "numero2": "-4124"
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3
