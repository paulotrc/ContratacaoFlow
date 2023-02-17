package br.paulotrc.contratacaoflow.iteractors;

import br.paulotrc.contratacaoflow.entities.ResponseAutomovelClienteData;
import br.paulotrc.contratacaoflow.entities.ResponseImovelClienteData;
import br.paulotrc.contratacaoflow.repositories.AutomovelRepository;
import br.paulotrc.contratacaoflow.repositories.ImovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AutomovelUseCase {

    private AutomovelRepository automovelRepository;

    public List<ResponseAutomovelClienteData> consultarAutomovelClientePeloCpf(String cpf){
        return automovelRepository.consultarAutomovelClientePeloCpf(cpf);
    }
}