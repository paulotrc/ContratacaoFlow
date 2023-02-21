package br.paulotrc.contratacaoflow.datasources.feign.configuration.bacen;

import org.springframework.context.annotation.Bean;

public class BacenClientConfiguration {

    @Bean
    public BacenErrorDecoder errorDecoder(){
        return new BacenErrorDecoder();
    }
}
