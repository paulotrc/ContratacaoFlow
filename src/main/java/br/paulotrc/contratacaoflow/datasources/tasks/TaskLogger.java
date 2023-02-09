package br.paulotrc.contratacaoflow.datasources.tasks;

import lombok.Setter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.BpmnModelException;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaskLogger implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskLogger.class);

    @Setter
    private Expression injectedProperty;

    @Override
    public void execute(DelegateExecution execution) {

        log.info("\n LoggerDelegate invocado com nome da atividade: '{}'," +
                        " processInstanceId: {}, businessKey: {}, executionId: {}, modelName: {}, elementId: {} \n",
                execution.getCurrentActivityName().replaceAll("\n", " "),
                execution.getProcessInstanceId(),
                execution.getProcessBusinessKey(),
                execution.getId(),
                execution.getBpmnModelInstance().getModel().getModelName(),
                execution.getBpmnModelElementInstance().getId()
        );

        // log process data
        log.info("--- Variables ---");
        Map<String, Object> variables = execution.getVariables();
        for (Map.Entry<String, Object> entry : variables.entrySet())
            log.info("data id: {} data value: {}",entry.getKey(), entry.getValue());

        // log injected property if set
        if (injectedProperty != null)
            log.info("Injected property: {}", injectedProperty.getValue(execution));

        // log extension properties if any are defined
        ExtensionElements extensionElements = execution.getBpmnModelElementInstance().getExtensionElements();
        if (extensionElements != null) {
            try {
                CamundaProperties camProps = extensionElements
                        .getElementsQuery().filterByType(CamundaProperties.class).singleResult();
                if (camProps != null) {
                    for (CamundaProperty prop : camProps.getCamundaProperties())
                        log.info("Camunda property {} with value {}", prop.getCamundaName(), prop.getCamundaValue());
                }
            } catch (BpmnModelException e) {}
        }
        log.info("\n");
    }

}
