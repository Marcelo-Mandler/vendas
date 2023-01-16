package com.mandler.marcelo.vendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Development
public class MyConfiguration {
    @Bean
    public CommandLineRunner init() {
        return args -> {
            System.out.println("Rodando a config de desenvolvimento.");
        };
    }
}
