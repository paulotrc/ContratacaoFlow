package br.paulotrc.contratacaoflow.datasources.feign.configuration.imovel;

import org.springframework.context.annotation.Bean;

public class ImovelClientConfiguration {

    @Bean
    public ImovelErrorDecoder errorDecoder(){
        return new ImovelErrorDecoder();
    }
}
