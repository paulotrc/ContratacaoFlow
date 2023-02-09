package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.ClienteClient;
import br.paulotrc.contratacaoflow.entities.ResponseClienteData;
import br.paulotrc.contratacaoflow.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ClienteDataSource implements ClienteRepository {

    private static final String MESSAGE_JSON_ERROR = "Falha ao recuperar os dados de Cliente!";

    private final ClienteClient clienteClient;

    @Override
    public ResponseClienteData consultarCliente(String cpf) {

        final ResponseClienteData response;
        try {
            response = clienteClient.consultarCliente(cpf);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_JSON_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
