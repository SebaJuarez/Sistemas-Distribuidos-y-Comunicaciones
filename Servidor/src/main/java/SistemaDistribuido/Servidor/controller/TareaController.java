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

import java.io.IOException;

@RestController
@RequestMapping("/tarea")
public class TareaController {


    @GetMapping()
    public RespuestaTareaDTO ejecutarTarea(@RequestBody ParametroTareaDTO parametroTarea) {
        String containerName = "servidortarea";
        String imageName = "dagyss/servidortarea";
        int port = 8080;

        try {
            // 1️⃣ Verificar si el contenedor ya está corriendo y detenerlo
            ejecutarComando("docker rm -f " + containerName);

            // 2️⃣ Levantar el contenedor con la imagen Docker
            ejecutarComando("docker run -d --name " + containerName + " -p " + port + ":8080 " + imageName);

            // 3️⃣ Esperar a que el contenedor esté listo
            esperarAQueEsteListo("http://localhost:" + port + "/ejecutarTarea/actuator/health");

            // 4️⃣ Hacer la petición HTTP con los parámetros de la tarea
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ParametroTareaDTO> request = new HttpEntity<>(parametroTarea, headers);

            ResponseEntity<RespuestaTareaDTO> response = restTemplate.exchange(
                    "http://localhost:" + port + "/ejecutarTarea", HttpMethod.POST, request, RespuestaTareaDTO.class
            );

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se conecto correctamente!");
        }
    }

    /**
     * Ejecuta un comando en la terminal.
     */
    private void ejecutarComando(String comando) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", comando);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        process.waitFor();
    }

    /**
     * Espera hasta que el servicio esté listo consultando su endpoint de salud.
     */
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
                Thread.sleep(2000); // Esperar 2 segundos antes de intentar de nuevo
            }
            intentos++;
        }
        throw new RuntimeException("El servicio no se levantó a tiempo.");
    }
}
