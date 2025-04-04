package SistemaDistribuido.Servidor.services;

import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;

public interface ContainerService {

    void startContainer();

    void borrarContainer();

    RespuestaTareaDTO ejecutarTareaRemota(Object parametroTarea);

}
