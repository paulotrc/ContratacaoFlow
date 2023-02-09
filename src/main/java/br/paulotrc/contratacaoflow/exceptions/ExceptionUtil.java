package br.paulotrc.contratacaoflow.exceptions;

import br.paulotrc.contratacaoflow.exceptions.http.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ExceptionUtil {

    public static ResourceException generateException(final String code, final String message, final String developerMessage, final String origin){
        return new ResourceException(code, message, developerMessage, origin);
    }

    public static InternalResourceException generateInternalResourceException(final String code, final String message, final String developerMessage, final String origin){
        return new InternalResourceException(code, message, developerMessage, origin);
    }

    public static String generateJsonFromException(final String code, final String message, final String developerMessage, final String origin) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(generateInternalResourceException(code, message, developerMessage, origin));
    }

    public static ResourceException generateExceptionFormJson(String jsonResourceException) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        InternalResourceException exception = mapper.readValue(jsonResourceException, InternalResourceException.class);
        return generateException(exception.getCode(), exception.getMessage(), exception.getDeveloperMessage(), exception.getOrigin());
    }

    public static void throwException(ResourceException e) {
        switch (e.getCode().substring(0, 3)) {
            case "400":
                throw new BadRequestException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            case "401":
                throw new UnauthorizedException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            case "404":
                throw new NotFoundException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            case "405":
                throw new MethodNotAllowedException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            case "406":
                throw new NotAcceptableException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            case "412":
                throw new PreconditionFailedException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            case "415":
                throw new UnsupportedMediaTypeException(e.getMessage(), e.getDeveloperMessage(), e.getCode(),
                        e.getOrigin());
            case "422":
                throw new UnprocessableEntityException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
            default:
                throw new InternalServerErrorException(e.getMessage(), e.getDeveloperMessage(), e.getCode(), e.getOrigin());
        }
    }
}