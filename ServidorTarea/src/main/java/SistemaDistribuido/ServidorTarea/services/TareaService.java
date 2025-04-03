package SistemaDistribuido.ServidorTarea.services;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;

public interface TareaService {

    ResultadoTareaDTO realizarTarea(ParametroTareaDTO parametroTarea);

}
