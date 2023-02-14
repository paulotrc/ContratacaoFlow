package br.paulotrc.contratacaoflow.iteractors;

import br.paulotrc.contratacaoflow.entities.ResponseImovelClienteData;
import br.paulotrc.contratacaoflow.repositories.ImovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImovelUseCase {

    private ImovelRepository imovelRepository;

    public List<ResponseImovelClienteData> consultarImovelCliente(String cpf){
        return imovelRepository.consultarImovelCliente(cpf);
    }
}
