package org.example.erd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ErdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErdApplication.class, args);
    }

}
