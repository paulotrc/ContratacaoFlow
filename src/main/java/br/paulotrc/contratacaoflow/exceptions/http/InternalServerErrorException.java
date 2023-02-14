package br.paulotrc.contratacaoflow.exceptions.http;

import br.paulotrc.contratacaoflow.exceptions.ResourceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InternalServerErrorException extends ResourceException {
        private static final long serialVersionUID = 1L;
        private String code;
        private String message;
        private String developerMessage;
        private String origin;

        @Override
        public String toString() {
            return "InternalServerErrorException{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", developerMessage='" + developerMessage + '\'' +
                    ", origin='" + origin + '\'' +
                    '}';
        }
}
