package br.paulotrc.contratacaoflow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ResponseRestricaoBacen {

        private UUID id;
        private String cpf;
        private Boolean temRestricao;
        private Boolean temAutomovel;
        private BigDecimal renda;

}
