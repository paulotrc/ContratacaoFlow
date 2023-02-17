package br.paulotrc.contratacaoflow.datasources.feign.configuration.automovel;

import br.paulotrc.contratacaoflow.entities.ResponseAutomovelClienteData;
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

            ResponseAutomovelClienteData responseAutomovelClienteData = objectMapper.readValue(body, ResponseAutomovelClienteData.class);

            return new GatewayResourceIntegrationRuntimeException(
                    responseAutomovelClienteData.getStatus().getErros().getMsgErro(),
                    responseAutomovelClienteData.getStatus().getCodigo(),
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
