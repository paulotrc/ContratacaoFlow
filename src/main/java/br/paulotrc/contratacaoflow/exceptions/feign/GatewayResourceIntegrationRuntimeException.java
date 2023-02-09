package br.paulotrc.contratacaoflow.exceptions.feign;

import br.paulotrc.contratacaoflow.exceptions.http.InternalHttpException;
import org.springframework.http.HttpStatus;

public class GatewayResourceIntegrationRuntimeException extends InternalHttpException {

    private final HttpStatus httpStatusCode;

    public GatewayResourceIntegrationRuntimeException(
            String userMessage,
            String developerMessage,
            HttpStatus httpStatusCode,
            String origin
    ) {

        super(userMessage, developerMessage, httpStatusCode, origin);
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatusCode;
    }

}
