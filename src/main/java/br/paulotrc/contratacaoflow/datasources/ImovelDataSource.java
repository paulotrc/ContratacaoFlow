package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.ImovelClient;
import br.paulotrc.contratacaoflow.entities.ResponseClienteData;
import br.paulotrc.contratacaoflow.entities.ResponseImovelClienteData;
import br.paulotrc.contratacaoflow.repositories.ImovelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ImovelDataSource implements ImovelRepository {

    private static final String MESSAGE_JSON_ERROR = "Falha ao recuperar os dados de Cliente!";

    private ImovelClient imovelClient;

    @Override
    public List<ResponseImovelClienteData> consultarImovelCliente(String cpf) {

        final List<ResponseImovelClienteData> response;
        try {
            response = imovelClient.consultarImovelCliente(cpf);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_JSON_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
