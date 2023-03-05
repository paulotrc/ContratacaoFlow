package br.paulotrc.contratacaoflow.datasources.feign.configuration.riscocliente;

import org.springframework.context.annotation.Bean;

public class RiscoClienteClientConfiguration {

    @Bean
    public RiscoClienteErrorDecoder errorDecoder(){
        return new RiscoClienteErrorDecoder();
    }
}
