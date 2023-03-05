package br.paulotrc.contratacaoflow.datasources.tasks;

import br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables;
import br.paulotrc.contratacaoflow.datasources.MensagemDataSource;
import br.paulotrc.contratacaoflow.entities.cliente.ResponseClienteData;
import br.paulotrc.contratacaoflow.exceptions.BussinessException;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.repositories.ClienteRepository;
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

import java.util.List;

@Component
@AllArgsConstructor
public class TaskConsultarCliente implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarCliente.class);

    private ClienteRepository clienteRepository;

    @Override
    public void execute(DelegateExecution execution) throws JsonProcessingException {

        try {
            log.info("TaskConsultarCliente - Inicio");
            final String cpf = execution.getVariable(CamundaProcessVariables.CPF).toString();

            final List<ResponseClienteData> responseClienteData = clienteRepository.consultarCliente(cpf);

            if(0 == responseClienteData.size()){
                throw new BussinessException();
            }

            execution.setVariable(CamundaProcessVariables.NOME, responseClienteData.get(0).getNome());
            execution.setVariable(CamundaProcessVariables.TEM_IMOVEL, responseClienteData.get(0).getTemImovel());
            execution.setVariable(CamundaProcessVariables.TEM_AUTOMOVEL, responseClienteData.get(0).getTemAutomovel());
            execution.setVariable(CamundaProcessVariables.RENDA, responseClienteData.get(0).getRenda());
            log.info("TaskConsultarCliente - Fim");

        } catch (BussinessException e) {
            log.error(MensagemDataSource.Erro.LOG, "Cliente não encontrado", "Cliente não encontrado", "");
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.BAD_REQUEST.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, "Cliente não encontrado",
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (BpmnModelException e) {
            execution.setVariable("ERROR_TECNICO_CLIENTE", TaskConsultarCliente.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_CLIENTE, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            execution.setVariable("ERROR_TECNICO_CLIENTE", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());
        }
    }
}
