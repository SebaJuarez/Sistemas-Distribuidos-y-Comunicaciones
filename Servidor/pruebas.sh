#!/bin/bash

# Hacer la solicitud HTTP POST y capturar la respuesta
echo -e "\n Enviando solicitudes POST a /tarea..."
echo -e "\n OPERACION_BASICA: 443 * (-4)"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_BASICA",
          "operador": "*",
          "parametro1": 443,
          "parametro2": -4
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3

echo -e "\n OPERACION_COMPARACION: 54 >= 43"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_COMPARACION",
          "operador": ">=",
          "parametro1": 54,
          "parametro2": 43
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3

echo -e "\n OPERACION_LOGICA: true XOR false"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_LOGICA",
          "operador": "XOR",
          "parametro1": true,
          "parametro2": false
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3

echo -e "\n OPERACION_INEXISTENTE: (4SAFD 23 -4124)"
RESPONSE=$(curl -s -X POST "http://localhost:8080/tarea" \
     -H "Content-Type: application/json" \
     -d '{
          "tipoTarea": "OPERACION_INEXISTENTE",
          "operador": "23",
          "parametro1": 4SAFD,
          "parametro2": -4124
         }')
# Mostrar la respuesta
echo -e "\n Respuesta del servidor:"
echo "$RESPONSE"

sleep 3
