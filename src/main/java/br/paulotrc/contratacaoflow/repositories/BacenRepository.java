package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.bacen.ResponseRestricaoBacenData;

import java.util.List;

public interface BacenRepository {


    List<ResponseRestricaoBacenData> consultarRestricaoBacen(String cpf);
}
