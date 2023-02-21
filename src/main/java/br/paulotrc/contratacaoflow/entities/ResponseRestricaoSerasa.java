package br.paulotrc.contratacaoflow.entities;

import br.paulotrc.contratacaoflow.entities.enumerados.serasa.TipoRestricaoSerasa;
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
public class ResponseRestricaoSerasa {

        private UUID id;
        private String cpf;
        private Boolean temRestricao;
        private TipoRestricaoSerasa tipoRestricaoSerasa;
        private BigDecimal valorRestricao;
        private Status status;
}
