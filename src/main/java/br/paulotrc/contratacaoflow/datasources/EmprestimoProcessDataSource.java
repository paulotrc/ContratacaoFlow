package br.paulotrc.contratacaoflow.datasources;

import br.paulotrc.contratacaoflow.configs.utils.EngineUtil;
import br.paulotrc.contratacaoflow.entities.EmprestimoRequest;
import br.paulotrc.contratacaoflow.entities.EmprestimoResponse;
import br.paulotrc.contratacaoflow.exceptions.ExceptionUtil;
import br.paulotrc.contratacaoflow.exceptions.ResourceException;
import br.paulotrc.contratacaoflow.repositories.EmprestimoProcessRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables.*;

@Service
public class EmprestimoProcessDataSource implements EmprestimoProcessRepository {

    private static final Logger log = LoggerFactory.getLogger(EmprestimoProcessDataSource.class);

    @Override
    public EmprestimoResponse executeProcess(EmprestimoRequest emprestimoRequest) throws ResourceException {
        final VariableMap variables = Variables.createVariables();
        final ObjectMapper mapper = new ObjectMapper();
        UUID bussinessKey = UUID.randomUUID();
        ProcessInstanceWithVariables instance;
        try{
            variables.putValue(BUSSINESS_KEY, bussinessKey.toString());
            variables.putValue(CPF,  Variables.objectValue(emprestimoRequest.getCpfVar()));

            instance = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceByKey(EMPRESTIMO_PROCESS_NAME)
                    .businessKey(bussinessKey.toString()).setVariables(variables)
                    .executeWithVariablesInReturn();

        }catch (Exception e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.INTERNAL_ERROR_EXCEPTION, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_CLIENTE);
            throw resourceException;
        }

        lancaExceptionEmCasoDeErroNoProcesso(instance.getVariables().entrySet().stream().filter(erro -> erro.getKey().contains("ERROR_TECNICO")).collect(Collectors.toUnmodifiableList()));

        return preencheResponse(
                instance.getProcessInstanceId(),
                instance.getVariables().get(BUSSINESS_KEY).toString(),
                instance.getVariables().get(CPF).toString(),
                instance.getVariables().get(NOME).toString(),
                ((Double)((Map<String, Object>)instance.getVariables().get(RISCO)).get(PERCENTUAL_LIBERADO)),
                ((String)((Map<String, Object>)instance.getVariables().get(RISCO)).get(NIVEL)),
                BigDecimal.valueOf(Double.valueOf(instance.getVariables().get(RENDA).toString())));
    }

    private void lancaExceptionEmCasoDeErroNoProcesso(List<Map.Entry<String, Object>> errorList) throws ResourceException {

        for (Map.Entry<String, Object> entry: errorList) {
            ResourceException resourceException;
            try {
                resourceException = ExceptionUtil.generateExceptionFormJson(entry.getValue().toString());
                throw resourceException;
            } catch (JsonProcessingException e) {
                ResourceException jsonException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        MensagemDataSource.MessageDataSource.JSON_ERROR_EXCEPTION, e.getMessage(),
                        MensagemDataSource.Origem.SERVICE_CLIENTE);
                throw jsonException;
            }
        }
    }

    public EmprestimoResponse preencheResponse(
            String processInstanceId,
            String bussinessKey,
            String cpf,
            String nome,
            Double percentualLiberado,
            String risco,
            BigDecimal renda)
            throws ResourceException {
        final EmprestimoResponse responseDto = EmprestimoResponse
                .builder()
                .cpf(cpf)
                .nome(nome)
                .processInstanceId(processInstanceId)
                .bussinessKey(bussinessKey)
                .percentualLiberado(percentualLiberado)
                .risco(risco)
                .renda(renda)
                .valorLiberadoEmprestimo(calculaValorLiberado(renda, percentualLiberado))
                .build();

        return responseDto;
    }

    private BigDecimal calculaValorLiberado(BigDecimal renda, Double percentualLiberado) {
        return (renda.multiply(BigDecimal.valueOf(percentualLiberado)).divide(BigDecimal.valueOf(100)));
    }
}