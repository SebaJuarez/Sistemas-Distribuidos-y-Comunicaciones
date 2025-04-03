package SistemaDistribuido.Servidor.configuracion;

import SistemaDistribuido.Servidor.dtos.ErrorRespuestaDTO;
import SistemaDistribuido.Servidor.exceptions.CommandExecutionException;
import SistemaDistribuido.Servidor.exceptions.ContainerNotStartedException;
import SistemaDistribuido.Servidor.exceptions.ImageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommandExecutionException.class)
    public ResponseEntity<ErrorRespuestaDTO> handleCommandExecutionException(CommandExecutionException e) {
        ErrorRespuestaDTO errorResponse = new ErrorRespuestaDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error en la ejecución del comando",
                "No se pudo ejecutar el comando en el servidor",
                Map.of(
                        "comando", e.getComando(),
                        "causa", e.getMessage()
                )
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ErrorRespuestaDTO> handleImageNotFoundException(ImageNotFoundException e) {
        ErrorRespuestaDTO errorResponse = new ErrorRespuestaDTO(
                HttpStatus.BAD_GATEWAY,
                "Imagen de contenedor no encontrada",
                "No se pudo encontrar la imagen '" + e.getMessage() + "' en el servidor Docker",
                Map.of("imagen", e.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }

    @ExceptionHandler(ContainerNotStartedException.class)
    public ResponseEntity<ErrorRespuestaDTO> handleContainerNotStartedException(ContainerNotStartedException e) {
        ErrorRespuestaDTO errorResponse = new ErrorRespuestaDTO(
                HttpStatus.GATEWAY_TIMEOUT,
                "Timeout al iniciar el contenedor",
                "El contenedor '" + e.getMessage() + "' no respondió a tiempo.",
                Map.of("contenedor", e.getMessage())
        );
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(errorResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorRespuestaDTO> manejarExcepcionFeign(ResponseStatusException ex) {
        ErrorRespuestaDTO errorDTO = new ErrorRespuestaDTO(
                HttpStatus.valueOf(ex.getStatusCode().value()),
                "Error en el servidor de tareas",
                ex.getReason(),
                Collections.emptyMap()
        );

        return ResponseEntity.status(ex.getStatusCode()).body(errorDTO);
    }

}
