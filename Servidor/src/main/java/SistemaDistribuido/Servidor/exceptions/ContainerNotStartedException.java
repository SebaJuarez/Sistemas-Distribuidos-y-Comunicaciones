package SistemaDistribuido.Servidor.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
@Getter
@Setter
public class ContainerNotStartedException extends RuntimeException {

    private final String containerName;
    private final String containerImage;

    public ContainerNotStartedException(String message, String containerName, String containerImage) {
        super(message);
        this.containerName = containerName;
        this.containerImage = containerImage;
    }
}
