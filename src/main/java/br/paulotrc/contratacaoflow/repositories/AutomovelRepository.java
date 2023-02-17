package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.ResponseAutomovelClienteData;
import br.paulotrc.contratacaoflow.entities.ResponseImovelClienteData;

import java.util.List;

public interface AutomovelRepository {


    List<ResponseAutomovelClienteData> consultarAutomovelClientePeloCpf(String cpf);
}
