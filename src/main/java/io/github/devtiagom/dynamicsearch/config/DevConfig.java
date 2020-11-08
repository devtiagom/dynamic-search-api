package io.github.devtiagom.dynamicsearch.config;

import io.github.devtiagom.dynamicsearch.services.H2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private final H2Service h2Service;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Autowired
    public DevConfig(H2Service h2Service) {
        this.h2Service = h2Service;
    }

    @Bean
    public boolean populateDevDatabase() {
        if (!"create".equals(this.strategy)) return false;
        this.h2Service.H2DatabaseSeeding();
        return true;
    }
}
