package br.paulotrc.contratacaoflow.entities.spc;

import br.paulotrc.contratacaoflow.entities.Status;
import br.paulotrc.contratacaoflow.entities.enumerados.spc.TipoRestricaoSpc;
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
public class ResponseRestricaoSpcData {

        private UUID id;
        private String cpf;
        private Boolean temRestricao;
        private TipoRestricaoSpc tipoRestricaoSpc;
        private BigDecimal valorRestricao;
        private Status status;
}
