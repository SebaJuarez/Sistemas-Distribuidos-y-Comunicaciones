package SistemaDistribuido.Servidor.configuracion;

import SistemaDistribuido.Servidor.dtos.ErrorDTO;
import SistemaDistribuido.Servidor.exceptions.ErrorTareaException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String responseBody = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            int codigoEstado = jsonNode.get("codigoEstado").intValue();
            String mensaje = jsonNode.has("mensaje") ? jsonNode.get("mensaje").asText() : "Error desconocido";
            String detalle = jsonNode.has("detalle") ? jsonNode.get("detalle").asText() : "No hay detalles";
            ErrorDTO errorDTO = new ErrorDTO(codigoEstado, mensaje, detalle);
            return new ErrorTareaException(errorDTO);

        } catch (Exception e) {
            return new ErrorTareaException(new ErrorDTO(response.status(), "Error desconocido", "No se pudo procesar la respuesta del servidor de tareas."));
        }
    }
}
