package com.project.template.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.flyway", name = "enabled", havingValue = "true")
public class FlywayConfiguration {

    @Bean
    public FlywayProperties flywayProperties() {
        return new FlywayProperties();
    }

    @Bean
    public Flyway flyway(FlywayProperties flywayProperties) {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(flywayProperties.getUrl(), flywayProperties.getUser(), flywayProperties.getPassword());
        flyway.setSchemas(flywayProperties.getSchemas().toArray(new String[0]));
        flyway.setPlaceholders(flywayProperties.getPlaceholders());
        return flyway;
    }

    @Bean
    public FlywayMigrationInitializer flywayMigrationInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway);
    }
}
