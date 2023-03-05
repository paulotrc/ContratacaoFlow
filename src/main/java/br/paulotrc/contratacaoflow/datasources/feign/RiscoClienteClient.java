package br.paulotrc.contratacaoflow.datasources.feign;

import br.paulotrc.contratacaoflow.datasources.feign.configuration.riscocliente.RiscoClienteClientConfiguration;
import br.paulotrc.contratacaoflow.entities.cliente.ResponseClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.RequestRiscoClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.ResponseRiscoClienteData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@FeignClient(name = "riscocliente",
        url = "${svc.riscocliente.url}",
        configuration = RiscoClienteClientConfiguration.class
)
public interface RiscoClienteClient {

    @GetMapping("/{cpf}")
    @Headers({"Content-Type: application/json"})
    List<ResponseRiscoClienteData> consultarRiscoCliente(@PathVariable(name = "cpf") String cpf);

    @PostMapping("/")
    @Headers({"Content-Type: application/json"})
    ResponseRiscoClienteData gavarRiscoCliente(@Valid @RequestBody RequestRiscoClienteData requestRiscoClienteData);

}
