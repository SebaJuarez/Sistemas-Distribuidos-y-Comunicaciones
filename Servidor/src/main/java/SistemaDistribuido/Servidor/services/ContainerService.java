package SistemaDistribuido.Servidor.services;

import SistemaDistribuido.Servidor.dtos.ParametroTareaDTO;
import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import org.springframework.http.ResponseEntity;

public interface ContainerService {

    void startContainer();

    void borrarContainer();

    RespuestaTareaDTO ejecutarTareaRemota(ParametroTareaDTO parametroTarea);

}
