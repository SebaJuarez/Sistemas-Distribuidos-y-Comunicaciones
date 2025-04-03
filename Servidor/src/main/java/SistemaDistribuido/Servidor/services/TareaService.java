package SistemaDistribuido.Servidor.services;

import SistemaDistribuido.Servidor.dtos.ParametroTareaDTO;
import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;

public interface TareaService {
    RespuestaTareaDTO realizarTarea(ParametroTareaDTO parametroTarea);
}
