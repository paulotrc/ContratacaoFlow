package br.paulotrc.contratacaoflow.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private String developerMessage;
    private String origin;

    @Override
    public String toString() {
        return "ResourceException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}