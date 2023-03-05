package br.paulotrc.contratacaoflow.entities.automovel;

import br.paulotrc.contratacaoflow.entities.Status;
import br.paulotrc.contratacaoflow.entities.enumerados.automovel.TipoAutomovel;
import br.paulotrc.contratacaoflow.entities.enumerados.automovel.TipoRestricaoAutomovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAutomovelData {

        private UUID id;
        private String cpf; //Documento de identificação do dono do automovel
        private String placa;
        private String renavam;
        private String categoria;
        private String combustivel;
        private String marca;
        private String modelo;
        private String anoFabricacao;
        private String anoModelo;
        private String cor;
        private String potencia;
        private Boolean financiado;
        private LocalDate dataCompra;
        private LocalDate dataFimContrato;
        private LocalDate dataQuitacao;
        private Integer parcelasTotais;
        private Integer parcelasPagas;
        private TipoRestricaoAutomovel restricaoAutomovel;
        private TipoAutomovel tipoAutomovel;

        private Status status;

}
