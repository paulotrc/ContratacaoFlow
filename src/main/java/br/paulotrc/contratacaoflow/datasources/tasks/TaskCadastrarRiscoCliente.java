package br.paulotrc.contratacaoflow.datasources.tasks;

import br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables;
import br.paulotrc.contratacaoflow.datasources.MensagemDataSource;
import br.paulotrc.contratacaoflow.entities.enumerados.riscocliente.TipoRestricaoRiscoCliente;
import br.paulotrc.contratacaoflow.entities.enumerados.riscocliente.TipoRiscoCliente;
import br.paulotrc.contratacaoflow.entities.riscocliente.RequestRiscoClienteData;
import br.paulotrc.contratacaoflow.entities.riscocliente.ResponseRiscoClienteData;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.repositories.RiscoClienteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.BpmnModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.*;

@Component
@AllArgsConstructor
public class TaskCadastrarRiscoCliente implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskCadastrarRiscoCliente.class);

    private RiscoClienteRepository riscoClienteRepository;

    @Override
    public void execute(DelegateExecution execution) throws JsonProcessingException {

        try {
            log.info("TaskCadastrarRiscoCliente - Inicio");
            final String cpf = execution.getVariable(CamundaProcessVariables.CPF).toString();
            final Boolean podeTomarEmprestimo = Boolean.valueOf(execution.getVariable(CamundaProcessVariables.PODE_TOMAR_EMPRESTIMO).toString());
            final Map tipoRiscoCliente = (Map) execution.getVariable(CamundaProcessVariables.RISCO);
            final List<TipoRestricaoRiscoCliente> restricoesRiscoCliente = montaRestricoes(execution);
            final RequestRiscoClienteData requestRiscoClienteData = RequestRiscoClienteData
                    .builder()
                    .cpf(cpf)
                    .riscoAtivo(Boolean.TRUE)
                    .podeTomarEmprestimo(podeTomarEmprestimo)
                    .validadeEmMeses(12)
                    .tipoRiscoCliente(TipoRiscoCliente.getTipoRiscoClientePelaDescricaoNivel(tipoRiscoCliente.get(CamundaProcessVariables.NIVEL).toString()))
                    .restricoesRiscoCliente(restricoesRiscoCliente)
                    .dataInclusao(LocalDate.now())
                    .idProcessInstanceExecution(execution.getProcessInstanceId())
                    .bussinessKeyExecution(execution.getProcessBusinessKey())
                    .build();

            final ResponseRiscoClienteData responseRiscoClienteData = riscoClienteRepository.cadastrarRiscoCliente(requestRiscoClienteData);

            log.info("TaskCadastrarRiscoCliente - Fim");
        } catch (BpmnModelException e) {

            execution.setVariable("ERROR_TECNICO_RISCO_CLIENTE", TaskCadastrarRiscoCliente.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_RISCO_CLIENTE", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_RISCO_CLIENTE", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_RISCO_CLIENTE", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());
        }
    }

    private List<TipoRestricaoRiscoCliente> montaRestricoes(DelegateExecution execution) {
        final Map<String, Boolean> restricoes = new HashMap<>();
        if(execution.getVariables().containsKey(CamundaProcessVariables.RESTRICAO_SPC)) {
            restricoes.put(TipoRestricaoRiscoCliente.SPC.name(), Boolean.valueOf(execution.getVariable(CamundaProcessVariables.RESTRICAO_SPC).toString()));
        }
        if(execution.getVariables().containsKey(CamundaProcessVariables.RESTRICAO_SERASA)) {
            restricoes.put(TipoRestricaoRiscoCliente.SERASA.name(), Boolean.valueOf(execution.getVariable(CamundaProcessVariables.RESTRICAO_SERASA).toString()));
        }
        if(execution.getVariables().containsKey(CamundaProcessVariables.RESTRICAO_BACEN)) {
            restricoes.put(TipoRestricaoRiscoCliente.BACEN.name(), Boolean.valueOf(execution.getVariable(CamundaProcessVariables.RESTRICAO_BACEN).toString()));
        }
        if(execution.getVariables().containsKey(CamundaProcessVariables.SUSPEITA_DE_FRAUDE)) {
            restricoes.put(TipoRestricaoRiscoCliente.SUSPEITA_FRAUDE.name(), Boolean.valueOf(execution.getVariable(CamundaProcessVariables.SUSPEITA_DE_FRAUDE).toString()));
        }
        if(execution.getVariables().containsKey(CamundaProcessVariables.DECLARACAO_DE_AUTOMOVEL_INVALIDA)) {
            restricoes.put(TipoRestricaoRiscoCliente.INFO_AUTOMOVEL_IRREGULAR.name(), Boolean.valueOf(execution.getVariable(CamundaProcessVariables.DECLARACAO_DE_AUTOMOVEL_INVALIDA).toString()));
        }
        if(execution.getVariables().containsKey(CamundaProcessVariables.DECLARACAO_DE_IMOVEL_INVALIDA)){
            restricoes.put(TipoRestricaoRiscoCliente.INFO_IMOVEL_IRREGULAR.name(), Boolean.valueOf(execution.getVariable(CamundaProcessVariables.DECLARACAO_DE_IMOVEL_INVALIDA).toString()));
        }
        return TipoRestricaoRiscoCliente.getIncludedRestrictions(restricoes);
    }
}
