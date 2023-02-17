package br.paulotrc.contratacaoflow.entities.enumerados.serasa;

public enum TipoRestricaoSerasa {
    DIVIDA("Dívida"),
    SITUACAO_CADASTRAL("Situação Cadastral"),
    CPF_CANCELADO("CPF Cancelado"),
    CPF_PENDENTE("CPF Pendente"),
    CPF_NULO("CPF Nulo");

    private String descricao;

    TipoRestricaoSerasa(String descricao) {
        this.descricao = descricao;
    }
}
