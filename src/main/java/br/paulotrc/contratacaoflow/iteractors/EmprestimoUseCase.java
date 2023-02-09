package br.paulotrc.contratacaoflow.iteractors;

import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import br.paulotrc.contratacaoflow.repositories.EmprestimoProcessRepository;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoUseCase {

    private EmprestimoProcessRepository emprestimoProcessRepository;

    public EmprestimoUseCase(EmprestimoProcessRepository emprestimoProcessRepository) {
        this.emprestimoProcessRepository = emprestimoProcessRepository;
    }

    public EmprestimoResponse execute (EmprestimoRequest emprestimoRequest){
        return emprestimoProcessRepository.executeProcess(emprestimoRequest);
    }
}
