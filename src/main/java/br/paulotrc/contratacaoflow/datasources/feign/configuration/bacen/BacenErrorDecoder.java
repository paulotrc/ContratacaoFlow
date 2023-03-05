package br.paulotrc.contratacaoflow.datasources.feign.configuration.bacen;

import br.paulotrc.contratacaoflow.entities.bacen.ResponseRestricaoBacenData;
import br.paulotrc.contratacaoflow.exceptions.feign.GatewayResourceIntegrationRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class BacenErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            ResponseRestricaoBacenData responseRestricaoBacenData = objectMapper.readValue(body, ResponseRestricaoBacenData.class);

            return new GatewayResourceIntegrationRuntimeException(
                    responseRestricaoBacenData.getStatus().getErros().getMsgErro(),
                    responseRestricaoBacenData.getStatus().getCodigo(),
                    HttpStatus.valueOf(response.status()),
                    "INTERNAL");

        } catch (IOException e) {
            return new GatewayResourceIntegrationRuntimeException(response.reason(),
                    response.body().toString(),
                    HttpStatus.valueOf(response.status()),
                    "INTERNAL");
        }
    }

}
