package SistemaDistribuido.ServidorTarea.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperacionNoSoportadaException extends RuntimeException{

    private Character operacionesNoSoportada;

    public OperacionNoSoportadaException(Character operacionesNoSoportada){
        super(String.format("Operacion no soportada %c . Solo aceptamos +,-,*,/", operacionesNoSoportada));
        this.operacionesNoSoportada = operacionesNoSoportada;
    }




}
