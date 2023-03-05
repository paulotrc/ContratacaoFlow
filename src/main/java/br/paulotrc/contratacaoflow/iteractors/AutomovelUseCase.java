package br.paulotrc.contratacaoflow.iteractors;

import br.paulotrc.contratacaoflow.entities.automovel.ResponseAutomovelData;
import br.paulotrc.contratacaoflow.repositories.AutomovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AutomovelUseCase {

    private AutomovelRepository automovelRepository;

    public List<ResponseAutomovelData> consultarAutomovelClientePeloCpf(String cpf){
        return automovelRepository.consultarAutomovelClientePeloCpf(cpf);
    }
}
