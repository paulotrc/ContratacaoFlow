package br.paulotrc.contratacaoflow.entities.bacen;

import br.paulotrc.contratacaoflow.entities.Status;
import br.paulotrc.contratacaoflow.entities.enumerados.bacen.TipoRestricaoBacen;
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
public class ResponseRestricaoBacenData {

        private UUID id;
        private String cpf;
        private Boolean temRestricao;
        private TipoRestricaoBacen tipoRestricaoBacen;
        private BigDecimal valorRestricao;
        private Status status;
}
