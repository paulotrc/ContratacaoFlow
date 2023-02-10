package br.paulotrc.contratacaoflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties
@EnableFeignClients
@EnableScheduling
public class ContratacaoflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContratacaoflowApplication.class, args);
    }

}
