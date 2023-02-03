package br.paulotrc.contratacaoflow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrquestradorRequest {

    private long cpfVar;

}
