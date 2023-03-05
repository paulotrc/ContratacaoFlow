package br.paulotrc.contratacaoflow.iteractors;

import br.paulotrc.contratacaoflow.entities.imovel.ResponseImovelData;
import br.paulotrc.contratacaoflow.repositories.ImovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImovelUseCase {

    private ImovelRepository imovelRepository;

    public List<ResponseImovelData> consultarImovelCliente(String cpf){
        return imovelRepository.consultarImovelCliente(cpf);
    }
}
