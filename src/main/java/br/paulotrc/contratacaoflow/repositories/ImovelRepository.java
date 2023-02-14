package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.ResponseClienteData;
import br.paulotrc.contratacaoflow.entities.ResponseImovelClienteData;

import java.util.List;

public interface ImovelRepository {


    List<ResponseImovelClienteData> consultarImovelCliente(String cpf);
}
