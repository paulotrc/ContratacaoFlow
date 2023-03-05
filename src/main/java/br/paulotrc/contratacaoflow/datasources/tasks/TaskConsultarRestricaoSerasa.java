package br.paulotrc.contratacaoflow.datasources.tasks;

import br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables;
import br.paulotrc.contratacaoflow.datasources.MensagemDataSource;
import br.paulotrc.contratacaoflow.entities.serasa.ResponseRestricaoSerasaData;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.repositories.SerasaRepository;
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
public class TaskConsultarRestricaoSerasa implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarRestricaoSerasa.class);

    private SerasaRepository serasaRepository;

    @Override
    public void execute(DelegateExecution execution) throws JsonProcessingException {

        try {
            log.info("TaskConsultarRestricaoSerasa - Inicio");
            final String cpf = execution.getVariable(CamundaProcessVariables.CPF).toString();

            final List<ResponseRestricaoSerasaData> responseRestricaoSerasaList = serasaRepository.consultarRestricaoSerasa(cpf);

            execution.setVariable(CamundaProcessVariables.RESTRICAO_SERASA, responseRestricaoSerasaList.size() > 0);
            log.info("TaskConsultarRestricaoSerasa - Fim");
        } catch (BpmnModelException e) {

            execution.setVariable("ERROR_TECNICO_SERASA", TaskConsultarRestricaoSerasa.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_SERASA, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_SERASA);
            execution.setVariable("ERROR_TECNICO_SERASA", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_SERASA, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_SERASA);
            execution.setVariable("ERROR_TECNICO_SERASA", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_SERASA, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_SERASA);
            execution.setVariable("ERROR_TECNICO_SERASA", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());
        }
    }
}
