package SistemaDistribuido.Servidor.feignClient;

import SistemaDistribuido.Servidor.configuracion.FeignConfig;
import SistemaDistribuido.Servidor.dtos.ParametroTareaDTO;
import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tareaClient", url = "${container.url}", configuration = FeignConfig.class)
public interface TareaFeignClient {

    @PostMapping(value = "${container.endpoint.ejecutarTarea}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RespuestaTareaDTO> ejecutarTarea(@RequestBody ParametroTareaDTO parametroTarea);

    @GetMapping(value = "${container.endpoint.health}")
    ResponseEntity<String> healthCheck();
}