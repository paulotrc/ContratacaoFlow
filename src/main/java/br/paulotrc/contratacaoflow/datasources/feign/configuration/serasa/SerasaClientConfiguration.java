package br.paulotrc.contratacaoflow.datasources.feign.configuration.serasa;

import org.springframework.context.annotation.Bean;

public class SerasaClientConfiguration {

    @Bean
    public SerasaErrorDecoder errorDecoder(){
        return new SerasaErrorDecoder();
    }
}
