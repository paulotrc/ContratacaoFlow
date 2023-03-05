package br.paulotrc.contratacaoflow.datasources.tasks;

import br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables;
import br.paulotrc.contratacaoflow.datasources.MensagemDataSource;
import br.paulotrc.contratacaoflow.entities.imovel.ResponseImovelData;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.repositories.ImovelRepository;
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
import java.util.List;

@Component
@AllArgsConstructor
public class TaskConsultarBemImovel implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarBemImovel.class);

    private ImovelRepository imovelRepository;

    @Override
    public void execute(DelegateExecution execution) throws JsonProcessingException {

        try {
            log.info("TaskConsultarBemImovel - Inicio");
            final String cpf = execution.getVariable(CamundaProcessVariables.CPF).toString();
            final Boolean declaraTerImovel = Boolean.valueOf(execution.getVariable(CamundaProcessVariables.TEM_IMOVEL).toString());

            final List<ResponseImovelData> responseImovelData = imovelRepository.consultarImovelCliente(cpf);

            if((declaraTerImovel && responseImovelData.size() > 0)){
                execution.setVariable(CamundaProcessVariables.DECLARACAO_DE_IMOVEL_INVALIDA, false);
            }else{
                execution.setVariable(CamundaProcessVariables.DECLARACAO_DE_IMOVEL_INVALIDA, true);
                execution.setVariable(CamundaProcessVariables.SUSPEITA_DE_FRAUDE, true);
            }
            final Boolean existemParcelasEmAberto = imoveisTemParcelaEmAberto(responseImovelData);

            final Boolean dataFimContratoEmAberto = imoveisDataFimContratoEmAberto(responseImovelData);
            execution.setVariable(CamundaProcessVariables.DATA_FIM_CONTRATO_MAIOR_QUE_ATUAL, dataFimContratoEmAberto);
            execution.setVariable(CamundaProcessVariables.PARCELAS_EM_ABERTO, existemParcelasEmAberto);

            log.info("TaskConsultarBemImovel - Fim");
        } catch (BpmnModelException e) {

            execution.setVariable("ERROR_TECNICO_IMOVEL", TaskConsultarBemImovel.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_IMOVEL, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_IMOVEL);
            execution.setVariable("ERROR_TECNICO_IMOVEL", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_IMOVEL, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_IMOVEL);
            execution.setVariable("ERROR_TECNICO_IMOVEL", jsonException);
            throw new BpmnError("ERROR", "ERROR", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_IMOVEL, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_IMOVEL);
            execution.setVariable("ERROR_TECNICO_IMOVEL", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR", "ERROR", e.getCause());
        }
    }

    private Boolean imoveisTemParcelaEmAberto(List<ResponseImovelData> responseImovelData) {
        return responseImovelData.stream().allMatch(n -> {
            return (n.getParcelasPagas() < n.getParcelasTotais());
        });
    }

    private Boolean imoveisDataFimContratoEmAberto(List<ResponseImovelData> responseImovelData) {
        return responseImovelData.stream().allMatch(n -> {
            return (LocalDate.now().isBefore(n.getDataFimContrato()));
        });
    }
}
