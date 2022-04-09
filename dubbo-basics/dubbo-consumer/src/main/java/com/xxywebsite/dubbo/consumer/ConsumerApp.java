package com.xxywebsite.dubbo.consumer;


import com.xxywebsite.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApp {


    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @Bean
    public CommandLineRunner testRunner() {
        return args -> {
            System.out.println(helloService.hello());
        };

    }
}
