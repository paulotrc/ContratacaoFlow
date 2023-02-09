package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import br.paulotrc.contratacaoflow.exceptions.ResourceException;

public interface EmprestimoProcessRepository {

    EmprestimoResponse executeProcess(EmprestimoRequest requestProcessDto) throws ResourceException;
}
