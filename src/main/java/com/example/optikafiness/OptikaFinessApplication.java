package com.example.optikafiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OptikaFinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(OptikaFinessApplication.class, args);
    }

}
