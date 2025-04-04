package SistemaDistribuido.Servidor.dtos;


import SistemaDistribuido.Servidor.domain.TipoTarea;

public record ParametroTareaDTO(TipoTarea tipoTarea, String operador, Object parametro1, Object parametro2) {
}
