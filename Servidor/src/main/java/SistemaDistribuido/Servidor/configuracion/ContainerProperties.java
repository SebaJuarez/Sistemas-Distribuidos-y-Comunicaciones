package SistemaDistribuido.Servidor.configuracion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "container")
@Getter
@Setter
public class ContainerProperties {
    private String name;
    private String image;
    private String port;
    private String url;
    private String endpointHealth;
}
