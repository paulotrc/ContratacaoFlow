package br.paulotrc.contratacaoflow.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoResponse {

    private String processInstanceId;
    private String bussinessKey;
    private String cpf;
    private String nome;
    private String risco;
    private Double percentualLiberado;
    private BigDecimal valorLiberadoEmprestimo;
    private BigDecimal renda;
}
