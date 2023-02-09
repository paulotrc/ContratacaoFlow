package br.paulotrc.contratacaoflow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ResponseClienteData {

        private UUID id;
        private String nome;
        private String cpf;
        private String ddd;
        private String telefone;
        private Boolean temImovel;
        private Boolean temAutomovel;
        private BigDecimal renda;

        private Status status;

}
