package SistemaDistribuido.Servidor.exceptions;

import SistemaDistribuido.Servidor.dtos.ErrorDTO;

public class ErrorTareaException extends RuntimeException {
    private final ErrorDTO errorDTO;

    public ErrorTareaException(ErrorDTO errorDTO) {
        super(errorDTO.mensaje());
        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }
}