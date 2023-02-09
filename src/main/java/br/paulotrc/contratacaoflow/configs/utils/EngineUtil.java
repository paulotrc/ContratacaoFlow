package br.paulotrc.contratacaoflow.configs.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;

import java.util.List;
import static br.paulotrc.contratacaoflow.configs.utils.CamundaProcessVariables.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EngineUtil {

    public static EngineUtil getInstance(){
        return new EngineUtil();
    }

    public String getActualProcessByBusinessKey(String businessKey) {
        ProcessInstance processInstance = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
        return processInstance.getProcessDefinitionId();
    }

    public String getActualActivityByBusinessKey(String businessKey) {
        String msg = "";
        try {
            List<ProcessInstance> processInstances = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceQuery().active().processInstanceBusinessKey(businessKey).list();
            ProcessInstance processInstance = processInstances.get(1);

            ActivityInstance activityInstance = EngineUtil.getInstance().getRuntimeEngine().getActivityInstance(processInstance.getId());
            String processName = activityInstance.getActivityName();
            String taskName = activityInstance.getChildActivityInstances()[0].getActivityName();
            msg = "BusinessKey " + businessKey + " is in Process: " + processName + " and Task: " + taskName;
        } catch (Exception e) {
            msg = "Business Key not existent or Process terminated.";
        }
        return msg;
    }

    public ProcessEngine getProcessEngine(){
        ProcessEngine processEngine = BpmPlatform.getDefaultProcessEngine();
        return processEngine;
    }

    public RuntimeService getRuntimeEngine(){
        ProcessEngine processEngine = BpmPlatform.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        return runtimeService;
    }

    public ExternalTaskService getExternalTaskService() {
        ProcessEngine processEngine = BpmPlatform.getDefaultProcessEngine();
        ExternalTaskService  externalTaskService = processEngine.getExternalTaskService();
        return externalTaskService;
    }

    public ExternalTask getExternalTask(String processInstanceId) {
        ExternalTaskService  externalTaskService = EngineUtil.getInstance().getExternalTaskService();
        ExternalTask externalTask = externalTaskService.createExternalTaskQuery().active().processInstanceId(processInstanceId).singleResult();
        return externalTask;
    }

    public ProcessInstance getProcessInstanceByBusinessKey(String businessKey, String processName) {
        ProcessInstance processInstance = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceQuery().processInstanceBusinessKey(businessKey, processName).singleResult();
        return processInstance;
    }

    public VariableInstance getVariableByProcessId(String processId, String varName) {
        VariableInstance variable = EngineUtil.getInstance().getRuntimeEngine().createVariableInstanceQuery().processInstanceIdIn(processId)
                .variableName(varName)
                .singleResult();
        return variable;
    }

    public Object getVariableObject(String processId, String varName) {
        return EngineUtil.getInstance().getRuntimeEngine().getVariable(processId, EMPRESTIMO_PROCESS_NAME);
    }

    public ExternalTask getExternalTaskByBusinessKey(String businessKey, String processName) {
        ProcessInstance processInstance = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceQuery().processInstanceBusinessKey(businessKey, processName).singleResult();
        String processInstanceId = processInstance.getId();
        ExternalTaskService  externalTaskService = EngineUtil.getInstance().getExternalTaskService();
        ExternalTask externalTask = externalTaskService.createExternalTaskQuery().active().processInstanceId(processInstanceId).singleResult();
        return externalTask;
    }
}