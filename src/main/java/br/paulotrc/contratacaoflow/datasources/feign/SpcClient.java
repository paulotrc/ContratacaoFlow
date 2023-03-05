package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.spc.SpcClientConfiguration;
import br.paulotrc.contratacaoflow.entities.spc.ResponseRestricaoSpcData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "spc",
        url = "${svc.spc.url}",
        configuration = SpcClientConfiguration.class
)
public interface SpcClient {

    @GetMapping("/cpf/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseRestricaoSpcData> consultarRestricaoSpcCliente(@PathVariable(name = "cpf") String cpf);

}
