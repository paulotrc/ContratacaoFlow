package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.serasa.SerasaClientConfiguration;
import br.paulotrc.contratacaoflow.entities.serasa.ResponseRestricaoSerasaData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "serasa",
        url = "${svc.serasa.url}",
        configuration = SerasaClientConfiguration.class
)
public interface SerasaClient {

    @GetMapping("/cpf/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseRestricaoSerasaData> consultarRestricaoSerasaCliente(@PathVariable(name = "cpf") String cpf);

}
