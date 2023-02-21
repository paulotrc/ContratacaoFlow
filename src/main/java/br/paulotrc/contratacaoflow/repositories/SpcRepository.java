package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.ResponseRestricaoBacen;
import br.paulotrc.contratacaoflow.entities.ResponseRestricaoSpc;

import java.util.List;

public interface SpcRepository {


    List<ResponseRestricaoSpc> consultarRestricaoSpc(String cpf);
}
