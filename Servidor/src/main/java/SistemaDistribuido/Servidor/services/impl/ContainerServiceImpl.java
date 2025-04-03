package SistemaDistribuido.Servidor.services.impl;

import SistemaDistribuido.Servidor.configuracion.ContainerProperties;
import SistemaDistribuido.Servidor.dtos.ParametroTareaDTO;
import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import SistemaDistribuido.Servidor.exceptions.CommandExecutionException;
import SistemaDistribuido.Servidor.exceptions.ContainerNotStartedException;
import SistemaDistribuido.Servidor.feignClient.TareaFeignClient;
import SistemaDistribuido.Servidor.services.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {

    private final ContainerProperties containerProperties;
    private final TareaFeignClient tareaFeignClient;

    @Override
    public void startContainer() {
        try {
            levantarImagen();

            System.out.println(imagenExiste(containerProperties.getImage()));

            System.out.println(containerExiste(containerProperties.getName()));

            esperarAQueEsteListo(containerProperties.getUrl() + containerProperties.getEndpointHealth());

        } catch (Exception e) {
            e.printStackTrace();
            throw new ContainerNotStartedException(e.getMessage().toString(), containerProperties.getName(), containerProperties.getImage());
        }
    }


    @Override
    public void borrarContainer() {
        if (imagenExiste(containerProperties.getImage())) {
            ejecutarComando("docker rm -f " + containerProperties.getName());
        }
    }

    @Override
    public RespuestaTareaDTO ejecutarTareaRemota(ParametroTareaDTO parametroTarea) {
        return tareaFeignClient.ejecutarTarea(parametroTarea).getBody();
    }

    private void levantarImagen() {
        if (!imagenExiste(containerProperties.getImage())) {
            ejecutarComando("docker pull " + containerProperties.getImage());
        }

        ejecutarComando("docker run -d --name " + containerProperties.getName() +
                " -p " + containerProperties.getPort() + ":" + containerProperties.getPort() +
                " -v /var/run/docker.sock:/var/run/docker.sock " +
                containerProperties.getImage());
    }

    private boolean imagenExiste(String imageName) {
        try {
            String output = ejecutarComando("docker images -q " + imageName);
            return !output.trim().isEmpty();
        } catch (CommandExecutionException e) {
            return false;
        }
    }

    private String ejecutarComando(String comando) {
        try {
            Process process = Runtime.getRuntime().exec(comando);
            process.waitFor();

            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            return output.toString();

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CommandExecutionException(comando, e);
        }
    }

    private void esperarAQueEsteListo(String url) throws InterruptedException {
        int intentos = 0;
        while (intentos < 10) {
            try {
                ResponseEntity<String> response = tareaFeignClient.healthCheck();
                if (response.getStatusCode().is2xxSuccessful()) {
                    System.out.println("esta listo " + response.getBody().toString());
                    return;
                }
            } catch (Exception e) {
                Thread.sleep(2000);
            }
            intentos++;
        }
        throw new ContainerNotStartedException("El servicio no se levantó a tiempo.", containerProperties.getName(), containerProperties.getImage());
    }
    private boolean containerExiste(String name) {
        try {
            String output = ejecutarComando("docker ps -a --filter name=" + name + " --format \"{{.Names}}\"");
            return !output.trim().isEmpty();
        } catch (CommandExecutionException e) {
            return false;
        }
    }

}
