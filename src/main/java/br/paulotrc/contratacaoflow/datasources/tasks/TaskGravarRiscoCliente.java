package br.paulotrc.contratacaoflow.datasources.tasks;

import br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables;
import br.paulotrc.contratacaoflow.datasources.MensagemDataSource;
import br.paulotrc.contratacaoflow.entities.ResponseClienteData;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.repositories.ClienteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Component
public class TaskGravarRiscoCliente implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskGravarRiscoCliente.class);

    private ClienteRepository clienteRepository;

    public TaskGravarRiscoCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws JsonProcessingException {

        try {
            log.info("TaskConsultarCliente - Inicio");
            ObjectMapper mapper = new ObjectMapper();
            String cpf = execution.getVariable(CamundaProcessVariables.CPF).toString();

            ResponseClienteData responseClienteData = clienteRepository.consultarCliente(cpf);

            execution.setVariable(CamundaProcessVariables.TEM_IMOVEL, responseClienteData.getTemImovel());
            execution.setVariable(CamundaProcessVariables.TEM_AUTOMOVEL, responseClienteData.getTemAutomovel());
            execution.setVariable(CamundaProcessVariables.RENDA, responseClienteData.getRenda());
            log.info("TaskConsultarCliente - Fim");
        } catch (BpmnModelException e) {

            execution.setVariable("ERROR_TECNICO_CLIENTE", TaskGravarRiscoCliente.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_CLIENTE", "ERROR_CLIENTE", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            throw new BpmnError("ERROR_CLIENTE", "ERROR_CLIENTE", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            throw new BpmnError("ERROR_CLIENTE", "ERROR_CLIENTE", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_CLIENTE", "ERROR_CLIENTE", e.getCause());
        }
    }
}
