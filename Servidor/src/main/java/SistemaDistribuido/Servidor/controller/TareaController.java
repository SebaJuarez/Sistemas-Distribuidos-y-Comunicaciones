package SistemaDistribuido.Servidor.controller;

import SistemaDistribuido.Servidor.dtos.ParametroTareaDTO;
import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import javassist.expr.NewArray;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tarea")
public class TareaController {


    @GetMapping()
    public ResponseEntity<RespuestaTareaDTO> ejecutarTarea(@RequestBody ParametroTareaDTO parametroTarea) {
        String imageName = "mi-app-spring";
        String containerId = null;
        DockerClient dockerClient = null;

        try {
            // 1️⃣ Configurar el cliente Docker
            dockerClient = DockerClientBuilder.getInstance(DefaultDockerClientConfig.createDefaultConfigBuilder().build()).build();

            // 2️⃣ Verificar si la imagen existe, si no, descargarla
            dockerClient.pullImageCmd(imageName).exec(new PullImageResultCallback()).awaitCompletion();

            // 3️⃣ Crear y ejecutar el contenedor
            CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                    .withHostConfig(new HostConfig().withPortBindings(
                            new PortBinding(Ports.Binding.bindPort(8080), ExposedPort.tcp(8080))
                            )
                    ).exec();

            containerId = container.getId();

            dockerClient.startContainerCmd(containerId).exec();

            // 4️⃣ Esperar a que el contenedor esté listo
            String urlBase = "http://localhost:8080";
            esperarAQueEsteListo(urlBase + "/ejecutarTarea/actuator/health");

            // 5️⃣ Hacer la petición HTTP
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ParametroTareaDTO> request = new HttpEntity<>(parametroTarea, headers);

            ResponseEntity<RespuestaTareaDTO> response = restTemplate.exchange(
                    urlBase + "/ejecutarTarea", HttpMethod.GET, request, RespuestaTareaDTO.class);

            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 6️⃣ Parar y eliminar el contenedor al finalizar
            if (dockerClient != null && containerId != null) {
                dockerClient.stopContainerCmd(containerId).exec();
                dockerClient.removeContainerCmd(containerId).exec();
            }
        }
    }

    private void esperarAQueEsteListo(String url) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        int intentos = 0;
        while (intentos < 10) {
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    return;
                }
            } catch (Exception e) {
                Thread.sleep(2000);
            }
            intentos++;
        }
        throw new RuntimeException("El servicio no se levantó a tiempo.");
    }
}
