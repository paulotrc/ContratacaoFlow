package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.serasa.ResponseRestricaoSerasaData;

import java.util.List;

public interface SerasaRepository {


    List<ResponseRestricaoSerasaData> consultarRestricaoSerasa(String cpf);
}
