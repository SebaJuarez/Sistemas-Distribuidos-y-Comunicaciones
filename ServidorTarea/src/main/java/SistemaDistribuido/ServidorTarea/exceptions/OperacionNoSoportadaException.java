package SistemaDistribuido.ServidorTarea.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperacionNoSoportadaException extends HttpMessageNotReadableException {

    private final String detalle;

    public OperacionNoSoportadaException(String mensaje, String detalle) {
        super(mensaje);
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }
}
