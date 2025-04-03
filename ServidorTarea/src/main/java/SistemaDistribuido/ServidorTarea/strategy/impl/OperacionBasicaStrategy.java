package SistemaDistribuido.ServidorTarea.strategy.impl;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import SistemaDistribuido.ServidorTarea.strategy.TareaStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OperacionBasicaStrategy implements TareaStrategy {
    @Override
    public ResultadoTareaDTO realizarTarea(ParametroTareaDTO parametros) {
        Double num1 = ((Number) parametros.parametro1()).doubleValue();
        Double num2 = ((Number) parametros.parametro2()).doubleValue();
        String operador = parametros.operador();

        double resultado;
        try {
            switch (operador) {
                case "+" -> resultado = num1 + num2;
                case "-" -> resultado = num1 - num2;
                case "*" -> resultado = num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        return ResultadoTareaDTO.error("División por cero no permitida.");
                    }
                    resultado = BigDecimal.valueOf(num1 / num2)
                            .setScale(2, RoundingMode.HALF_UP).doubleValue(); // Redondeo
                }
                default -> {
                    return ResultadoTareaDTO.error("Operador inválido: " + operador);
                }
            }
            return ResultadoTareaDTO.exito(resultado);
        } catch (Exception e) {
            return ResultadoTareaDTO.error("Error al realizar operación: " + e.getMessage());
        }
    }
}
