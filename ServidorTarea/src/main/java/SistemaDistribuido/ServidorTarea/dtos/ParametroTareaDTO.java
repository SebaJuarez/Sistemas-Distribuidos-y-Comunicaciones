package SistemaDistribuido.ServidorTarea.dtos;

import SistemaDistribuido.ServidorTarea.domain.TipoTarea;
import jakarta.validation.constraints.*;

public record ParametroTareaDTO(

        @NotNull(message = "El tipo de tarea no puede ser nulo")
        TipoTarea tipoTarea,

        @NotNull(message = "El operador no puede ser nulo")
        @Pattern(
                regexp = "[-+*/]|AND|OR|XOR|>|<|>=|<=|==|!=",
                message = "Operador inválido. Debe ser uno de: +, -, *, /, AND, OR, XOR, >, <, >=, <=, ==, !="
        )
        String operador,

        @NotNull(message = "El primer parámetro no puede ser nulo")
        Object parametro1,

        @NotNull(message = "El segundo parámetro no puede ser nulo")
        Object parametro2
) {
        public ParametroTareaDTO {
                tipoTarea.validarParametros(operador, parametro1, parametro2);
        }
}