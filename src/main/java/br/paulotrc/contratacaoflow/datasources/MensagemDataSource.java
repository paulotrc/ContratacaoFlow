package br.paulotrc.contratacaoflow.datasources;

public final class MensagemDataSource {

    private MensagemDataSource(){
    }

    public static class Erro {
        private Erro(){
        }

        public static final String LOG = " Erro messsage : {} , cause: {}, stacktrace : {}";
    }

    public static class Origem {
        private Origem(){
        }

        public static final String SERVICE_CLIENTE = "SERVICE CLIENTE";
        public static final String SERVICE_IMOVEL = "SERVICE IMOVEL";
        public static final String SERVICE_AUTOMOVEL = "SERVICE AUTOMOVEL";
    }

    public static class MessageDataSource {

        private MessageDataSource() {
        }

        public static final String ERRO_CONSULTA_CLIENTE = "Erro ao consultar o cliente";
        public static final String ERRO_CONSULTA_IMOVEL = "Erro ao consultar o imóvel";
        public static final String ERRO_CONSULTA_AUTOMOVEL = "Erro ao consultar o automóvel";
        public static final String INTERNAL_ERROR_EXCEPTION = "ERRO AO EXECUTAR UMA OPERAÇÃO";
        public static final String JSON_ERROR_EXCEPTION = "ERRO AO EFETUAR UMA OPERAÇÃO COM JSON";

    }
}
