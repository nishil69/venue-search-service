package com.whitbread.venuesearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Application specific configuration is held here.
 * Instances of this will get injected where access to this configuration is required.
 */
@Configuration
@ConfigurationProperties(prefix="foursquare")
public class AppConfig {

    // These will be read from the "external" application.properties files.
    private String clientId;
    private String clientSecret;
    private String version;
    private String venuesExploreUri;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getVenuesExploreUri() { return venuesExploreUri; }
    public void setVenuesExploreUri(String venuesExploreUri) { this.venuesExploreUri = venuesExploreUri; }
}
