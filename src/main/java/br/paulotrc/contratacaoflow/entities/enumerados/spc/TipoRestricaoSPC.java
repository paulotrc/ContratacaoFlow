package br.paulotrc.contratacaoflow.entities.enumerados.spc;

public enum TipoRestricaoSPC {
    DIVIDA("Dívida"),
    SITUACAO_CADASTRAL("Situação Cadastral"),
    CPF_CANCELADO("CPF Cancelado"),
    CPF_PENDENTE("CPF Pendente"),
    CPF_NULO("CPF Nulo");

    private String descricao;

    TipoRestricaoSPC(String descricao) {
        this.descricao = descricao;
    }
}
