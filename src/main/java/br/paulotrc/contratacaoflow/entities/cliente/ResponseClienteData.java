package br.paulotrc.contratacaoflow.entities.cliente;

import br.paulotrc.contratacaoflow.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
