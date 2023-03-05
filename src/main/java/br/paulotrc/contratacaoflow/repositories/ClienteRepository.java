package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.cliente.ResponseClienteData;

import java.util.List;

public interface ClienteRepository {

//    @CircuitBreaker(name = "processServiceCliente", fallbackMethod = "fallback")
//    @Retry(name = "default")
    List<ResponseClienteData> consultarCliente(String cpf);
}
