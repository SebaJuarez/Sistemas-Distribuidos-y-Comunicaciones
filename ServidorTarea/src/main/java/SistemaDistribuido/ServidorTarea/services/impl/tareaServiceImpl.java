package SistemaDistribuido.ServidorTarea.services.impl;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import SistemaDistribuido.ServidorTarea.factory.TareaStrategyFactory;
import SistemaDistribuido.ServidorTarea.services.TareaService;
import SistemaDistribuido.ServidorTarea.strategy.TareaStrategy;
import org.springframework.stereotype.Service;

@Service
public class tareaServiceImpl implements TareaService {

    private TareaStrategy tareaStrategy;

    @Override
    public ResultadoTareaDTO realizarTarea(ParametroTareaDTO parametros) {

        tareaStrategy = TareaStrategyFactory.obtenerEstrategia(parametros.tipoTarea());

        if (tareaStrategy == null) {
            return ResultadoTareaDTO.error("No se encontró estrategia para el tipo de tarea: " + parametros.tipoTarea());
        }

        return tareaStrategy.realizarTarea(parametros);
    }
}
