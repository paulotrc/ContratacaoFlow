package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import br.paulotrc.contratacaoflow.entities.ResponseClienteData;
import br.paulotrc.contratacaoflow.exceptions.ResourceException;

public interface ClienteRepository {

    ResponseClienteData consultarCliente(String cpf);
}
