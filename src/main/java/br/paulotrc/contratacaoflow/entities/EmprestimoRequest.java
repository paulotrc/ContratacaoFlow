package br.paulotrc.contratacaoflow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmprestimoRequest {

    private String cpfVar;

}
