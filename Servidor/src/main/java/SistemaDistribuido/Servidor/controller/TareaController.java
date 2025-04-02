package SistemaDistribuido.Servidor.controller;

import SistemaDistribuido.Servidor.dtos.ParametroTareaDTO;
import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/tarea")
public class TareaController {


    @PostMapping()
    public RespuestaTareaDTO ejecutarTarea(@RequestBody ParametroTareaDTO parametroTarea) {
        String containerName = "tareaserver";
        String imageName = "dagyss/tareaserver";
        int port = 8080;

        try {
            if (!imagenExiste(imageName)) {
                ejecutarComando("docker pull " + imageName);
            }

            ejecutarComando("docker rm -f " + containerName);

            ejecutarComando("docker run -d --name " + containerName +
                    " -p " + port + ":8080 " +
                    "-v /var/run/docker.sock:/var/run/docker.sock " +
                    imageName);

            esperarAQueEsteListo("http://localhost:" + port + "/ejecutarTarea/actuator/health");

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ParametroTareaDTO> request = new HttpEntity<>(parametroTarea, headers);

            ResponseEntity<RespuestaTareaDTO> response = restTemplate.exchange(
                    "http://localhost:" + port + "/ejecutarTarea", HttpMethod.POST, request, RespuestaTareaDTO.class
            );

            ejecutarComando("docker rm -f " + containerName);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se conectó correctamente!");
        }
    }
    private boolean imagenExiste(String imageName) {
        try {
            String output = ejecutarComando("docker images -q " + imageName);
            return !output.trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ejecuta un comando en la terminal.
     */

    private String ejecutarComando(String comando) throws Exception {
        Process process = Runtime.getRuntime().exec(comando);
        process.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
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
