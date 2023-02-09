package br.paulotrc.contratacaoflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Erros {

    @JsonProperty("erro")
    private List<Erro> erro;
    @JsonProperty("msgErro")
    private String msgErro;

}
