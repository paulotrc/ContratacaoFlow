package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.SerasaClient;
import br.paulotrc.contratacaoflow.entities.serasa.ResponseRestricaoSerasaData;
import br.paulotrc.contratacaoflow.repositories.SerasaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SerasaDataSource implements SerasaRepository {

    private static final String MESSAGE_ERROR = "Falha ao recuperar os dados de restrições Serasa!";

    private SerasaClient serasaClient;

    @Override
    public List<ResponseRestricaoSerasaData> consultarRestricaoSerasa(String cpf) {

        final List<ResponseRestricaoSerasaData> response;
        try {
            response = serasaClient.consultarRestricaoSerasaCliente(cpf);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
