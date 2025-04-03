package SistemaDistribuido.ServidorTarea.strategy.impl;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import SistemaDistribuido.ServidorTarea.strategy.TareaStrategy;

public class OperacionComparacionStrategy implements TareaStrategy {
    @Override
    public ResultadoTareaDTO realizarTarea(ParametroTareaDTO parametros) {
        Double num1 = ((Number) parametros.parametro1()).doubleValue();
        Double num2 = ((Number) parametros.parametro2()).doubleValue();
        String operador = parametros.operador();

        boolean resultado;
        try {
            switch (operador) {
                case ">" -> resultado = num1 > num2;
                case "<" -> resultado = num1 < num2;
                case ">=" -> resultado = num1 >= num2;
                case "<=" -> resultado = num1 <= num2;
                case "==" -> resultado = num1.equals(num2);
                case "!=" -> resultado = !num1.equals(num2);
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
