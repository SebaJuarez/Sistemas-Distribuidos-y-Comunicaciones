package SistemaDistribuido.Servidor.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
@Getter
public class ImageNotFoundException extends RuntimeException {

    private final String imageName;

    public ImageNotFoundException(String imageName) {
        super("La imagen '" + imageName + "' no se encuentra en el servidor Docker local.");
        this.imageName = imageName;
    }
}