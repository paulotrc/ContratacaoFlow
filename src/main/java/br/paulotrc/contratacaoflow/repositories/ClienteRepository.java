package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import br.paulotrc.contratacaoflow.entities.ResponseClienteData;
import br.paulotrc.contratacaoflow.exceptions.ResourceException;

import java.util.List;

public interface ClienteRepository {

    List<ResponseClienteData> consultarCliente(String cpf);
}
