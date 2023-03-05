package br.paulotrc.contratacaoflow.entities.enumerados.riscocliente;

import java.util.Arrays;

public enum TipoRiscoCliente {

    ALTO("alto"),
    MEDIO("medio"),
    BAIXO("baixo");

    private String descricaoNivel;

    TipoRiscoCliente(String descricaoNivel) {
        this.descricaoNivel = descricaoNivel;
    }

    public static TipoRiscoCliente getTipoRiscoClientePelaDescricaoNivel(String descNivel){
        return Arrays.stream(TipoRiscoCliente.values()).filter(item -> {
            return item.descricaoNivel.equals(descNivel);
        }).findFirst().orElse(null);
    }
}
