package SistemaDistribuido.ServidorTarea.factory;

import SistemaDistribuido.ServidorTarea.domain.TipoTarea;
import SistemaDistribuido.ServidorTarea.strategy.TareaStrategy;
import SistemaDistribuido.ServidorTarea.strategy.impl.OperacionBasicaStrategy;
import SistemaDistribuido.ServidorTarea.strategy.impl.OperacionComparacionStrategy;
import SistemaDistribuido.ServidorTarea.strategy.impl.OperacionLogicaStrategy;

import java.util.Map;

public class TareaStrategyFactory {
    private static final Map<TipoTarea, TareaStrategy> estrategias = Map.of(
            TipoTarea.OPERACION_BASICA, new OperacionBasicaStrategy(),
            TipoTarea.OPERACION_LOGICA, new OperacionLogicaStrategy(),
            TipoTarea.OPERACION_COMPARACION, new OperacionComparacionStrategy()
    );

    public static TareaStrategy obtenerEstrategia(TipoTarea tipo) {
        return estrategias.getOrDefault(tipo, null);
    }
}
