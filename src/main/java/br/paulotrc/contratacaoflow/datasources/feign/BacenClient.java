package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.bacen.BacenClientConfiguration;
import br.paulotrc.contratacaoflow.entities.bacen.ResponseRestricaoBacenData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "bacen",
        url = "${svc.bacen.url}",
        configuration = BacenClientConfiguration.class
)
public interface BacenClient {

    @GetMapping("/cpf/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseRestricaoBacenData> consultarRestricaoBacenCliente(@PathVariable(name = "cpf") String cpf);

}
