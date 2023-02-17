package br.paulotrc.contratacaoflow.entities.enumerados.imovel;

public enum TipoRestricaoImovel {
    DIVIDA("Dívida"),
    SITUACAO_CADASTRAL("Situação Cadastral"),
    CPF_CANCELADO("CPF Cancelado"),
    CPF_PENDENTE("CPF Pendente"),
    CPF_NULO("CPF Nulo");

    private String descricao;

    TipoRestricaoImovel(String descricao) {
        this.descricao = descricao;
    }
}