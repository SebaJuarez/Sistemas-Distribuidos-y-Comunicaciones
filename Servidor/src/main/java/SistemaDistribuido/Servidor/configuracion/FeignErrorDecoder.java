package SistemaDistribuido.Servidor.configuracion;

import SistemaDistribuido.Servidor.dtos.ErrorRespuestaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String cuerpo = response.body() != null ?
                    new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8) : "";

            ErrorRespuestaDTO errorRespuesta = objectMapper.readValue(cuerpo, ErrorRespuestaDTO.class);

            HttpStatus status = HttpStatus.resolve(errorRespuesta.getStatus());
            if (status == null) status = HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseStatusException(status, errorRespuesta.getMessage());
        } catch (IOException e) {
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error procesando la respuesta del servidor de tareas");
        }
    }
}