package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.spc.ResponseRestricaoSpcData;

import java.util.List;

public interface SpcRepository {


    List<ResponseRestricaoSpcData> consultarRestricaoSpc(String cpf);
}
