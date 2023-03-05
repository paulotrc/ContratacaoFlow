package br.paulotrc.contratacaoflow.entities.riscocliente;

import br.paulotrc.contratacaoflow.entities.Status;
import br.paulotrc.contratacaoflow.entities.enumerados.riscocliente.TipoRestricaoRiscoCliente;
import br.paulotrc.contratacaoflow.entities.enumerados.riscocliente.TipoRiscoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ResponseRiscoClienteData {

    private UUID id;
    private String cpf; //Documento de identificação do dono do imóvel
    private LocalDate dataInclusao;
    private Integer validadeEmMeses;
    private TipoRiscoCliente tipoRiscoCliente;
    private List<TipoRestricaoRiscoCliente> restricoesRiscoCliente;
    private Boolean riscoAtivo;
    private Boolean podeTomarEmprestimo;
    private Status status;
}

