package SistemaDistribuido.ServidorTarea.dtos;

import jakarta.validation.constraints.*;

public record ParametroTareaDTO(
        @NotNull(message = "El operador no puede ser nulo")
        @Pattern(regexp = "[-+*/]", message = "El operador debe ser uno de los siguientes: +, -, *, /")
        String operador,  // <- Cambiado de Character a String

        @NotNull(message = "El primer número no puede ser nulo")
        @Min(value = -10000, message = "El primer número debe ser al menos -10000")
        @Max(value = 10000, message = "El primer número no puede ser mayor que 10000")
        Double numero1,  // <- Cambiado de Number a Double

        @NotNull(message = "El segundo número no puede ser nulo")
        @Min(value = -10000, message = "El segundo número debe ser al menos -10000")
        @Max(value = 10000, message = "El segundo número no puede ser mayor que 10000")
        Double numero2   // <- Cambiado de Number a Double
) {
}
