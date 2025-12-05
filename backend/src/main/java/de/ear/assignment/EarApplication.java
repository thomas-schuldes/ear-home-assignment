package de.ear.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        scanBasePackages = {
                "de.ear.assignment",
                "de.ear.backend"
        }
)
@EnableScheduling
public class EarApplication {

    public static void main(String[] args) {
        SpringApplication.run(EarApplication.class, args);
    }
}
