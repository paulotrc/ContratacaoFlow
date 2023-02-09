package br.paulotrc.contratacaoflow.transportlayers.impl;

import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.exceptions.ResourceException;
import br.paulotrc.contratacaoflow.iteractors.EmprestimoUseCase;
import br.paulotrc.contratacaoflow.transportlayers.EmprestimoResourceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmprestimoResource implements EmprestimoResourceI {

    private EmprestimoUseCase emprestimoUseCase;

    public EmprestimoResource(EmprestimoUseCase emprestimoUseCase) {
        this.emprestimoUseCase = emprestimoUseCase;
    }
    @Override
    public ResponseEntity<EmprestimoResponse> get(String cpf) {
        final EmprestimoRequest emprestimoRequest = EmprestimoRequest.builder().cpfVar(cpf).build();
        EmprestimoResponse emprestimoResponse = null;
        try {
            emprestimoResponse = emprestimoUseCase.execute(emprestimoRequest);    
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(emprestimoResponse);
    }
}
