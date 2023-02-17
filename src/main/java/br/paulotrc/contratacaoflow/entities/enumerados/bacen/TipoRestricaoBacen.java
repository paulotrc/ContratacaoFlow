package br.paulotrc.contratacaoflow.entities.enumerados.bacen;

public enum TipoRestricaoBacen {
    EXCLUSAO_CLIENTE("Decisão Judicial de Exclusão de Cliente", "Legal"),
    EXCLUSAO_OPERACAO("Decisão Judicial de Exclusão de Operações", "Legal"),
    MARCACAO_SUBJUDICE("Decisão Judicial de Marcação de Sub Júdice", "Legal"),
    VICIO_CONTRATO("Vício de Contrato", "Administrativa"),
    MANIFESTACAO_DISCORDANCIA("Manifestação de Discordância", "Administrativa");

    private String descricao;
    private String tipo;

    TipoRestricaoBacen(String descricao, String tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }
}
