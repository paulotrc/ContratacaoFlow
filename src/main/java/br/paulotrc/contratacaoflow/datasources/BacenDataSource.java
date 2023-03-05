package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.BacenClient;
import br.paulotrc.contratacaoflow.entities.bacen.ResponseRestricaoBacenData;
import br.paulotrc.contratacaoflow.repositories.BacenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BacenDataSource implements BacenRepository {

    private static final String MESSAGE_ERROR = "Falha ao recuperar os dados de restrições Bacen!";

    private BacenClient bacenClient;

    @Override
    public List<ResponseRestricaoBacenData> consultarRestricaoBacen(String cpf) {

        final List<ResponseRestricaoBacenData> response;
        try {
            response = bacenClient.consultarRestricaoBacenCliente(cpf);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
