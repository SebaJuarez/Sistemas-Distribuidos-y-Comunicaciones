package SistemaDistribuido.Servidor.services.impl;

import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import SistemaDistribuido.Servidor.services.ContainerService;
import SistemaDistribuido.Servidor.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TareaRemotaServiceImpl implements TareaService {

    private final ContainerService containerService;
    @Override
    public RespuestaTareaDTO realizarTarea(Object parametroTarea) {
        containerService.borrarContainer();
        containerService.startContainer();
        RespuestaTareaDTO respuestaTarea = containerService.ejecutarTareaRemota(parametroTarea);
        containerService.borrarContainer();
        return respuestaTarea;
    }
}
