package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.cliente.ClienteClientConfiguration;
import br.paulotrc.contratacaoflow.entities.cliente.ResponseClienteData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "cliente",
        url = "${svc.clientes.url}",
        configuration = ClienteClientConfiguration.class
)
public interface ClienteClient {

    @GetMapping("/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseClienteData> consultarCliente(@PathVariable(name = "cpf") String cpf);

}
