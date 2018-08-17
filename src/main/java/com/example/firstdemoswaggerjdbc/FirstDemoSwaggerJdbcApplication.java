package com.example.firstdemoswaggerjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FirstDemoSwaggerJdbcApplication {

    public static void main(String[] args) {

        SpringApplication.run(FirstDemoSwaggerJdbcApplication.class, args);
    }
}
