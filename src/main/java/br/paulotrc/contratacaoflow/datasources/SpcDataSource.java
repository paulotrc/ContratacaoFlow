package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.SpcClient;
import br.paulotrc.contratacaoflow.entities.ResponseRestricaoSpc;
import br.paulotrc.contratacaoflow.repositories.SpcRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SpcDataSource implements SpcRepository {

    private static final String MESSAGE_ERROR = "Falha ao recuperar os dados de restrições SPC!";

    private SpcClient spcClient;

    @Override
    public List<ResponseRestricaoSpc> consultarRestricaoSpc(String cpf) {

        final List<ResponseRestricaoSpc> response;
        try {
            response = spcClient.consultarRestricaoSpcCliente(cpf);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
