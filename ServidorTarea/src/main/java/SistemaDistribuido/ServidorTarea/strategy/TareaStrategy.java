package SistemaDistribuido.ServidorTarea.strategy;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;

public interface TareaStrategy {
    ResultadoTareaDTO realizarTarea(ParametroTareaDTO parametroTarea);
}
