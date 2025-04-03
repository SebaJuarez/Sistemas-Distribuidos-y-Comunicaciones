package SistemaDistribuido.Servidor.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class CommandExecutionException extends RuntimeException {
    private final String comando;
    private final String error;

    public CommandExecutionException(String comando, Exception e) {
        super("Error ejecutando comando dentro del servidor: " + comando, e);
        this.comando = comando;
        this.error = e.getMessage();
    }
}
