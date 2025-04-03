package SistemaDistribuido.ServidorTarea.dtos;

public record ErrorDTO(
        int codigoEstado,
        String mensaje,
        String detalle
) {
}