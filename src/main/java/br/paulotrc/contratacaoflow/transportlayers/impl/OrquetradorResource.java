package br.paulotrc.contratacaoflow.transportlayers.impl;

import br.paulotrc.contratacaoflow.entities.OrquestradorRequest;
import br.paulotrc.contratacaoflow.transportlayers.OrquestradorResourceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrquetradorResource implements OrquestradorResourceI {

    @Override
    public ResponseEntity<OrquestradorRequest> get(long cpf) {
        return new ResponseEntity<OrquestradorRequest>(HttpStatus.NOT_IMPLEMENTED);
    }
}
