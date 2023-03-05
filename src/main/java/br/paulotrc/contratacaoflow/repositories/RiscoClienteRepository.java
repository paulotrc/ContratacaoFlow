package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.cliente.ResponseClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.RequestRiscoClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.ResponseRiscoClienteData;

import java.util.List;

public interface RiscoClienteRepository {

    ResponseRiscoClienteData cadastrarRiscoCliente(RequestRiscoClienteData requestRiscoClienteData);
}
