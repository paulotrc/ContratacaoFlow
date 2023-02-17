package br.paulotrc.contratacaoflow.datasources.feign.configuration.automovel;

import org.springframework.context.annotation.Bean;

public class AutomovelClientConfiguration {

    @Bean
    public AutomovelErrorDecoder errorDecoder(){
        return new AutomovelErrorDecoder();
    }
}
