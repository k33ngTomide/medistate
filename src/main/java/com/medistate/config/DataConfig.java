package com.medistate.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DataConfig {

    @Value("${DATABASE_HOST}")
    private String databaseHost;

    @Value("${DATABASE_PORT}")
    private String databasePort;

    @Value("${DATABASE_NAME}")
    private String databaseName;

    @Value("${DATABASE_PASSWORD}")
    private String databasePassword;

    @Value("${DATABASE_USERNAME}")
    private String databaseUsername;


}
