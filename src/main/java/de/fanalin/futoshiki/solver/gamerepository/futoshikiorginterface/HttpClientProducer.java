package de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Produces a HTTP client
 */
@Configuration
public class HttpClientProducer {
    @Bean
    public HttpClient getHttpClient() {
        return HttpClients.createDefault();
    }
}
