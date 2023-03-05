package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.imovel.ImovelClientConfiguration;
import br.paulotrc.contratacaoflow.entities.imovel.ResponseImovelData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "imovel",
        url = "${svc.imovel.url}",
        configuration = ImovelClientConfiguration.class
)
public interface ImovelClient {

    @GetMapping("/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseImovelData> consultarImovelCliente(@PathVariable(name = "cpf") String cpf);

}
