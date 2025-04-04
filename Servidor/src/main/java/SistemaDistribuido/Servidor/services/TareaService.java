package SistemaDistribuido.Servidor.services;

import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;

public interface TareaService {
    RespuestaTareaDTO realizarTarea(Object parametroTarea);
}
