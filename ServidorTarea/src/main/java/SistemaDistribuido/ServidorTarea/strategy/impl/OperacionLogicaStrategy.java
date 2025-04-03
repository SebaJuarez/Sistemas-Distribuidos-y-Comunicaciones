package SistemaDistribuido.ServidorTarea.strategy.impl;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import SistemaDistribuido.ServidorTarea.strategy.TareaStrategy;

public class OperacionLogicaStrategy implements TareaStrategy {
    @Override
    public ResultadoTareaDTO realizarTarea(ParametroTareaDTO parametros) {
        Boolean bool1 = (Boolean) parametros.parametro1();
        Boolean bool2 = (Boolean) parametros.parametro2();
        String operador = parametros.operador();

        boolean resultado;
        try {
            switch (operador) {
                case "AND" -> resultado = bool1 && bool2;
                case "OR" -> resultado = bool1 || bool2;
                case "XOR" -> resultado = bool1 ^ bool2;
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
