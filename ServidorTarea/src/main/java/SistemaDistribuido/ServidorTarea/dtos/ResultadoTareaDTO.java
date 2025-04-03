package SistemaDistribuido.ServidorTarea.dtos;

public record ResultadoTareaDTO(
        boolean exito,
        String mensaje,
        Object resultado
) {
    public static ResultadoTareaDTO exito(Object resultado) {
        return new ResultadoTareaDTO(true, "Operación exitosa", resultado);
    }

    public static ResultadoTareaDTO error(String mensaje) {
        return new ResultadoTareaDTO(false, mensaje, null);
    }
}
