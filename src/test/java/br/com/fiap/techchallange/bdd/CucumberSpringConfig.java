package br.com.fiap.techchallange.bdd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfig {

    @Bean
    public SharedData sharedData() {
        return new SharedData();
    }
}