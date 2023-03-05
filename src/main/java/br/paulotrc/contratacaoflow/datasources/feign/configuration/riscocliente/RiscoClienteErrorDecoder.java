package br.paulotrc.contratacaoflow.datasources.feign.configuration.riscocliente;

import br.paulotrc.contratacaoflow.entities.cliente.ResponseClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.ResponseRiscoClienteData;
import br.paulotrc.contratacaoflow.exceptions.feign.GatewayResourceIntegrationRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class RiscoClienteErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            ResponseRiscoClienteData responseRiscoClienteData = objectMapper.readValue(body, ResponseRiscoClienteData.class);

            return new GatewayResourceIntegrationRuntimeException(
                    responseRiscoClienteData.getStatus().getErros().getMsgErro(),
                    responseRiscoClienteData.getStatus().getCodigo(),
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
