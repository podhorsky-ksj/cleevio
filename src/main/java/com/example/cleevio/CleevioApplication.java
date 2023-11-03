package com.example.cleevio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@ComponentScan("com.example.cleevi")
@EntityScan("com.example.cleevi")
@Configuration
public class CleevioApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleevioApplication.class, args);
    }

    /*
    @Configuration
    public class LoadDatabase {

        @Bean
        CommandLineRunner initDatabase(ProductsDao repository) {

            return args -> {
                repository.persist(new Product("PC", "Personal computer", 30000.0));
            };
        }
    }

     */
}


