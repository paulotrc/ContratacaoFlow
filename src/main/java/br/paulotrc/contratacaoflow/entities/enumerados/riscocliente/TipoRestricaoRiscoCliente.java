package br.paulotrc.contratacaoflow.entities.enumerados.riscocliente;

import java.util.*;

public enum TipoRestricaoRiscoCliente {
    SPC("Spc"),
    SERASA("Serasa"),
    BACEN("Bacen"),
    INFO_IMOVEL_IRREGULAR("Informações de Imóvel Irregulares"),
    INFO_AUTOMOVEL_IRREGULAR("Informações de Automóvel Irregulares"),
    SUSPEITA_FRAUDE("Suspeita de Fraude");

    private String descricao;

    TipoRestricaoRiscoCliente(String descricao) {
        this.descricao = descricao;
    }

    public static List<TipoRestricaoRiscoCliente> getIncludedRestrictions(Map<String, Boolean> restricoes) {
        List<TipoRestricaoRiscoCliente> restricoesRiscoCliente = new ArrayList<>();
        Arrays.stream(TipoRestricaoRiscoCliente.values()).forEach(item -> {
            if(restricoes.containsKey(item.name()) && restricoes.get(item.name()).booleanValue()){
                restricoesRiscoCliente.add(item);
            }
        });
        return restricoesRiscoCliente.isEmpty() ? null : restricoesRiscoCliente;
    }

}