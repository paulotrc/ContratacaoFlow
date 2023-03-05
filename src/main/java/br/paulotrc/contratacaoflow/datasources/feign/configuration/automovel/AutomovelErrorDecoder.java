package br.paulotrc.contratacaoflow.datasources.feign.configuration.automovel;

import br.paulotrc.contratacaoflow.entities.automovel.ResponseAutomovelData;
import br.paulotrc.contratacaoflow.exceptions.feign.GatewayResourceIntegrationRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class AutomovelErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            ResponseAutomovelData responseAutomovelData = objectMapper.readValue(body, ResponseAutomovelData.class);

            return new GatewayResourceIntegrationRuntimeException(
                    responseAutomovelData.getStatus().getErros().getMsgErro(),
                    responseAutomovelData.getStatus().getCodigo(),
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
