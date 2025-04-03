package SistemaDistribuido.Servidor.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorRespuestaDTO {

    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> detalles;

    public ErrorRespuestaDTO(HttpStatus status, String error, String message, Map<String, String> detalles) {
        this.status = status.value();
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.detalles = detalles;
    }
}
