package com.liti.puebacrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PuebacrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PuebacrudApplication.class, args);
    }

}
