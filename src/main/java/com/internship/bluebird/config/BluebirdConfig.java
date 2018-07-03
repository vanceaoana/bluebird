package com.internship.bluebird.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BluebirdConfig {

    private SpringProperties springProperties;

    @Autowired
    public BluebirdConfig(SpringProperties springProperties) {
        this.springProperties = springProperties;
    }

    @Bean
    public DataSource dataSource()
    {
        return DataSourceBuilder
                .create()
                .username(springProperties.getDatasource().getUsername())
                .password(springProperties.getDatasource().getPassword())
                .url(springProperties.getDatasource().getUrl())
                .driverClassName(springProperties.getDatasource().getDriverClassName())
                .build();
    }

}
