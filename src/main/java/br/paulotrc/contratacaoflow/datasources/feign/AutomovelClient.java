package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.automovel.AutomovelClientConfiguration;
import br.paulotrc.contratacaoflow.entities.automovel.ResponseAutomovelData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "automovel",
        url = "${svc.automovel.url}",
        configuration = AutomovelClientConfiguration.class
)
public interface AutomovelClient {

    @GetMapping("/cliente/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseAutomovelData> consultarAutomovelClientePeloCpf(@PathVariable(name = "cpf") String cpf);

}
