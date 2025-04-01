package SistemaDistribuido.ServidorTarea.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record ParametroTareaDTO(
        @NotNull(message = "El operador no puede ser nulo") @Pattern(regexp = "[-+*/]", message = "El operador debe ser uno de los siguientes: +, -, *, /") Character operador,

        @NotNull(message = "El primer número no puede ser nulo") @Min(value = -10000, message = "El primer número debe ser al menos -10000") @Max(value = 10000, message = "El primer número no puede ser mayor que 10000") Number numero1,

        @NotNull(message = "El segundo número no puede ser nulo") @Min(value = -10000, message = "El segundo número debe ser al menos -10000") @Max(value = 10000, message = "El segundo número no puede ser mayor que 10000") Number numero2) {
}
