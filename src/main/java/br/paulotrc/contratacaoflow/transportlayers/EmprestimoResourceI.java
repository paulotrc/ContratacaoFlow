package br.paulotrc.contratacaoflow.transportlayers;

import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Orquestrador", description = "Operações para orquestração")
@RequestMapping(value = "/api/v1")
public interface EmprestimoResourceI {

    @Operation(summary = "Executar uma operação de orquestração de empréstimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emprestimo executado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmprestimoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao executar",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping (value = "/execute/cpf/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<EmprestimoResponse> get(
            @Parameter(name = "cpf", description = "Número do CPF para empréstimo", required = true)
            @PathVariable("cpf") String cpf
    );
}
