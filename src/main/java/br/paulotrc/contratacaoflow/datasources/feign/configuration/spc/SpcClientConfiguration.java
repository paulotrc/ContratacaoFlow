package br.paulotrc.contratacaoflow.datasources.feign.configuration.spc;

import org.springframework.context.annotation.Bean;

public class SpcClientConfiguration {

    @Bean
    public SpcErrorDecoder errorDecoder(){
        return new SpcErrorDecoder();
    }
}
