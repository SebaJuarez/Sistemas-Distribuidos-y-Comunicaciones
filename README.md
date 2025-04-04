# Proyecto: Ejecución de Tareas Genéricas vía Docker + HTTP

Este proyecto implementa un sistema distribuido que permite ejecutar tareas genéricas dentro de un contenedor Docker a través de una API HTTP. Las tareas pueden ser operaciones básicas, lógicas o de comparación.

---

##  Iniciar el servidor
- Ejecutar el script `test.sh` ubicado en la carpeta `Servidor/` para construir e iniciar el contenedor del servidor.

```bash
cd Servidor
./test.sh
```

Este script compila el proyecto, construye la imagen Docker y levanta el servidor, que quedará escuchando en el puerto `8080`.

---

##  Ejecutar pruebas

Para probar el sistema con tareas ya definidas, ejecutá el script `pruebas.sh` desde la misma carpeta:

```bash
./pruebas.sh
```

---

##  API - Endpoint disponible

### `POST /tarea`

Ejecuta una tarea genérica dentro de un contenedor Docker.

- **URL**: `http://localhost:8080/tarea`
- **Método**: `POST`
- **Content-Type**: `application/json`
- **Request body**: Objeto JSON (ver más abajo).
- **Response**: Resultado de la operación en formato JSON.

---

##  Parámetros esperados

Los parámetros deben cumplir con el siguiente esquema:

```json
{
  "tipoTarea": "OPERACION_BASICA",
  "operador": "+",
  "parametro1": 15,
  "parametro2": 7
}
```

###  Campos esperados (`ParametroTareaDTO`)

| Campo        | Tipo     | Obligatorio | Descripción |
|--------------|----------|-------------|-------------|
| `tipoTarea`  | `String` (enum) | ✅ | Puede ser: `OPERACION_BASICA`, `OPERACION_LOGICA`, `OPERACION_COMPARACION`. |
| `operador`   | `String` | ✅ | Debe ser válido según el tipo de tarea. |
| `parametro1` | `Number` o `Boolean` | ✅ | Primer valor para la operación. |
| `parametro2` | `Number` o `Boolean` | ✅ | Segundo valor para la operación. |

---

##  Tipos de tarea y operadores válidos

| Tipo de tarea         | Operadores válidos                      | Tipo de parámetros        |
|------------------------|------------------------------------------|----------------------------|
| OPERACION_BASICA       | `+`, `-`, `*`, `/`                       | `Number`                   |
| OPERACION_LOGICA       | `AND`, `OR`, `XOR`                       | `Boolean`                  |
| OPERACION_COMPARACION  | `<`, `>`, `==`, `!=`, `<=`, `>=`         | `Number`                   |


---

##  Ejemplos con `curl`

### Ejemplo: suma (`OPERACION_BASICA` con `+`)

```bash
curl -X POST http://localhost:8080/tarea   -H "Content-Type: application/json"   -d '{
    "tipoTarea": "OPERACION_BASICA",
    "operador": "+",
    "parametro1": 10,
    "parametro2": 5
}'
```

### Ejemplo: operación lógica (`OPERACION_LOGICA` con `OR`)

```bash
curl -X POST http://localhost:8080/tarea   -H "Content-Type: application/json"   -d '{
    "tipoTarea": "OPERACION_LOGICA",
    "operador": "OR",
    "parametro1": true,
    "parametro2": false
}'
