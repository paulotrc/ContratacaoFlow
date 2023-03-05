package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.imovel.ResponseImovelData;

import java.util.List;

public interface ImovelRepository {


    List<ResponseImovelData> consultarImovelCliente(String cpf);
}
