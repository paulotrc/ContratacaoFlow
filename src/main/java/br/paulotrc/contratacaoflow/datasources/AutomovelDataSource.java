package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.datasources.feign.AutomovelClient;
import br.paulotrc.contratacaoflow.entities.automovel.ResponseAutomovelData;
import br.paulotrc.contratacaoflow.repositories.AutomovelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AutomovelDataSource implements AutomovelRepository {

    private static final String MESSAGE_ERROR = "Falha ao recuperar os dados de automóvel do Cliente!";

    private AutomovelClient automovelClient;

    @Override
    public List<ResponseAutomovelData> consultarAutomovelClientePeloCpf(String cpf) {

        final List<ResponseAutomovelData> response;
        try {
            response = automovelClient.consultarAutomovelClientePeloCpf(cpf);
        } catch (RuntimeException ex) {
            log.error(MESSAGE_ERROR, ex.getCause());
            throw ex;
        }
        return response;
    }
}
