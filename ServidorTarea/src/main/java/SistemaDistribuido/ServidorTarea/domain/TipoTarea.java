package SistemaDistribuido.ServidorTarea.domain;

import SistemaDistribuido.ServidorTarea.exceptions.OperacionNoSoportadaException;

import java.util.Set;

public enum TipoTarea {
    OPERACION_BASICA(Set.of("+", "-", "*", "/")),
    OPERACION_LOGICA(Set.of("AND", "OR", "XOR")),
    OPERACION_COMPARACION(Set.of("<", ">", "==", "!=", "<=", ">="));

    private final Set<String> operadoresValidos;

    TipoTarea(Set<String> operadoresValidos) {
        this.operadoresValidos = operadoresValidos;
    }

    public boolean esOperadorValido(String operador) {
        return operadoresValidos.contains(operador);
    }

    public void validarParametros(String operador, Object parametro1, Object parametro2) {
        if (!esOperadorValido(operador)) {
            throw new OperacionNoSoportadaException(
                    "Operador inválido para " + this,
                    "Debe ser uno de los siguientes: " + operadoresValidos
            );
        }

        boolean sonNumericos = parametro1 instanceof Number && parametro2 instanceof Number;
        boolean sonBooleanos = parametro1 instanceof Boolean && parametro2 instanceof Boolean;
        final String detalle = "Se recibieron: " + parametro1.getClass().getSimpleName() + ", " + parametro2.getClass().getSimpleName();

        switch (this) {
            case OPERACION_BASICA, OPERACION_COMPARACION -> {
                if (!sonNumericos) {
                    throw new OperacionNoSoportadaException(
                            this + ": Ambos parámetros deben ser numéricos.",
                            detalle
                    );
                }
            }
            case OPERACION_LOGICA -> {
                if (!sonBooleanos) {
                    throw new OperacionNoSoportadaException(
                            this + ": Ambos parámetros deben ser booleanos.",
                            detalle
                    );
                }
            }
        }
    }
}
