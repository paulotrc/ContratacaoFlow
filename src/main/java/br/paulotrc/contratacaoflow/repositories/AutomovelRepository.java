package br.paulotrc.contratacaoflow.repositories;

import br.paulotrc.contratacaoflow.entities.automovel.ResponseAutomovelData;

import java.util.List;

public interface AutomovelRepository {


    List<ResponseAutomovelData> consultarAutomovelClientePeloCpf(String cpf);
}
