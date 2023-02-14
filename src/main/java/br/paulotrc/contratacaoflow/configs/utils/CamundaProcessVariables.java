package br.paulotrc.contratacaoflow.configs.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CamundaProcessVariables {

    public static final String EMPRESTIMO_PROCESS_NAME = "OfertarEmprestimoFlowProcess";
    public static final String BUSSINESS_KEY = "bussinessKey";
    public static final String CPF = "cpf";
    public static final String STATUS = "status";

    public static final String TEM_IMOVEL = "temImovel";

    public static final String TEM_AUTOMOVEL = "temAutomovel";

    public static final String RENDA = "renda";

    public static final String RESULT_CALCULO_RISCO = "riscoResult";

    public static final String PERCENTUAL_LIBERADO = "percentualMax";

    public static final String RISCO = "nivel";

    public static final String DATA_FIM_CONTRATO_MAIOR_QUE_ATUAL = "dataFimContratoMaiorQueDataAtual";
    public static final String PARCELAS_EM_ABERTO = "parcelasEmAberto";

    public static final String DECLARACAO_DE_IMOVEL_INVALIDA = "declaracaoDeImovelInvalida";
    public static final String DECLARACAO_DE_AUTOMOVEL_INVALIDA = "declaracaoDeAutomovelInvalida";
}