package br.paulotrc.contratacaoflow.entities;

import br.paulotrc.contratacaoflow.entities.enumerados.imovel.TipoImovel;
import br.paulotrc.contratacaoflow.entities.enumerados.imovel.TipoRestricaoImovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ResponseImovelClienteData {

        private UUID id;
        private String cpf; //Documento de identificação do dono do imóvel
        private String cep;
        private String estado;
        private String cidade;
        private String bairro;
        private String endereco;
        private String numero;
        private String complemento;
        private String referencia;
        private LocalDate dataCompra;
        private LocalDate dataFimContrato;
        private LocalDate dataQuitacao;
        private Integer parcelasTotais;
        private Integer parcelasPagas;
        private TipoRestricaoImovel restricaoImovel;
        private TipoImovel tipoImovel;
        private Status status;

}
