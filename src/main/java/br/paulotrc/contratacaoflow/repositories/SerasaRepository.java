package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.ResponseRestricaoSerasa;
import br.paulotrc.contratacaoflow.entities.ResponseRestricaoSpc;

import java.util.List;

public interface SerasaRepository {


    List<ResponseRestricaoSerasa> consultarRestricaoSerasa(String cpf);
}
