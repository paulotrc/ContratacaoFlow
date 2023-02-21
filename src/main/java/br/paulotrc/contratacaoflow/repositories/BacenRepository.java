package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.ResponseImovelClienteData;
import br.paulotrc.contratacaoflow.entities.ResponseRestricaoBacen;

import java.util.List;

public interface BacenRepository {


    List<ResponseRestricaoBacen> consultarRestricaoBacen(String cpf);
}
