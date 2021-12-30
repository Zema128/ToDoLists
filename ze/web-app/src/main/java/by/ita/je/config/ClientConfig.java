package by.ita.je.config;

import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
@AllArgsConstructor
public class ClientConfig {
    private final EurekaClient eurekaClient;

    public String getUrl(){
        return eurekaClient.getNextServerFromEureka("data-base", false).getHomePageUrl() + "data_base-app";
    }
}