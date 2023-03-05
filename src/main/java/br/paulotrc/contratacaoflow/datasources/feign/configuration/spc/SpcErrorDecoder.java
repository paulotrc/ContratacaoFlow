package br.paulotrc.contratacaoflow.datasources.feign.configuration.spc;

import br.paulotrc.contratacaoflow.entities.spc.ResponseRestricaoSpcData;
import br.paulotrc.contratacaoflow.exceptions.feign.GatewayResourceIntegrationRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class SpcErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            ResponseRestricaoSpcData responseRestricaoSpc = objectMapper.readValue(body, ResponseRestricaoSpcData.class);

            return new GatewayResourceIntegrationRuntimeException(
                    responseRestricaoSpc.getStatus().getErros().getMsgErro(),
                    responseRestricaoSpc.getStatus().getCodigo(),
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
