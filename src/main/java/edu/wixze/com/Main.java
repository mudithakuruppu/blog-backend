package edu.wixze.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "edu.wixze.com")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}