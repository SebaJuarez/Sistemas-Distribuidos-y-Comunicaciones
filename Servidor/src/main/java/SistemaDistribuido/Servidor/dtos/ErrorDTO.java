package SistemaDistribuido.Servidor.dtos;

public record ErrorDTO(
        int codigoEstado,
        String mensaje,
        String detalle
) {
}