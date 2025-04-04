package SistemaDistribuido.ServidorTarea.configurations;

import SistemaDistribuido.ServidorTarea.dtos.ErrorDTO;
import SistemaDistribuido.ServidorTarea.exceptions.OperacionNoSoportadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OperacionNoSoportadaException.class)
    public ResponseEntity<ErrorDTO> manejarOperacionNoSoportada(OperacionNoSoportadaException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getDetalle()
        );
        return ResponseEntity.badRequest().body(errorDTO);
    }
}
