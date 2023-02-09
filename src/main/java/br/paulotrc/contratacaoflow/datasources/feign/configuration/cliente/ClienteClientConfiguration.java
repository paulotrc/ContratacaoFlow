package br.paulotrc.contratacaoflow.datasources.feign.configuration.cliente;

import org.springframework.context.annotation.Bean;

public class ClienteClientConfiguration {

    @Bean
    public ClienteErrorDecoder errorDecoder(){
        return new ClienteErrorDecoder();
    }
}
