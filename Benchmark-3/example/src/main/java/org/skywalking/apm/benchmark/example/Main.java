package org.skywalking.apm.benchmark.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("org.skywalking.apm.benchmark.example")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
