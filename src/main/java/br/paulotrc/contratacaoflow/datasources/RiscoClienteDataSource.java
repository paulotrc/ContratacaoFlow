package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.RiscoClienteClient;
import br.paulotrc.contratacaoflow.entities.riscocliente.RequestRiscoClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.ResponseRiscoClienteData;
import br.paulotrc.contratacaoflow.repositories.RiscoClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RiscoClienteDataSource implements RiscoClienteRepository {

    private static final String MESSAGE_ERROR = "Falha ao recuperar os dados de Risco Cliente!";

    private RiscoClienteClient riscoClienteClient;

    @Override
    public ResponseRiscoClienteData cadastrarRiscoCliente(RequestRiscoClienteData requestRiscoClienteData) {

        final ResponseRiscoClienteData response;
        try {
            response = riscoClienteClient.gavarRiscoCliente(requestRiscoClienteData);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
