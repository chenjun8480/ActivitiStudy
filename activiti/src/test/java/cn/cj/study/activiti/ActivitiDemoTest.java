package cn.cj.study.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExtensionAttribute;
import org.activiti.bpmn.model.FieldExtension;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

/**
 * 
 * @author jun.chen
 *
 */
public class ActivitiDemoTest {
	/**
	 * 获取简单示例
	 * 
	 * @return
	 */
	public String getSimpleActivitiXml(String processId) {
		BpmnModel model = getModel(processId);
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
		byte[] convertToXML = bpmnXMLConverter.convertToXML(model);
		return new String(convertToXML);
	}

	private Process getProcess(String processId) {
		Process process = new Process();
		process.setId(processId);
		process.setName("processName");
		return process;
	}



	public UserTask getTask() {
		UserTask task = new UserTask();
		task.setId("userTaskId");
		task.setName("userTaskName");
		// 指定处理人
		task.setAssignee("jun.chen");
		// 指定任务的候选处理人
		List<String> candidateUsers = new ArrayList<String>();
		candidateUsers.add("wenjing.li");
		task.setCandidateUsers(candidateUsers);
		// 制定候选
		List<String> candidateGroups = new ArrayList<String>();
		candidateGroups.add("group");
		task.setCandidateGroups(candidateGroups);
		// 添加监听
		List<ActivitiListener> taskListeners = new ArrayList<ActivitiListener>();
		ActivitiListener activitiListener = new ActivitiListener();
		/** 设置监听选项 create-创建任务 assignment-分配任务 complete-完成任务 delete-删除任务 */
		activitiListener.setEvent(TaskListener.EVENTNAME_CREATE);
		/** 指定处理监听的方式 Java的全路径类-class 表达式方式-express、delegateExpression */
		activitiListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION);
		activitiListener.setImplementation("${IBaseBeanService.showTest()}");
		taskListeners.add(activitiListener);
		
		ActivitiListener activitiListener_end = new ActivitiListener();
		activitiListener_end.setEvent(TaskListener.EVENTNAME_ALL_EVENTS);
		activitiListener_end.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
		activitiListener_end.setImplementation("cn.cj.study.activiti.listener.Demo_taskListener");
		FieldExtension fieldExtension_name = new FieldExtension();
		fieldExtension_name.setFieldName("name");
		fieldExtension_name.setStringValue("exxxxxx");
		FieldExtension fieldExtension_code = new FieldExtension();
		fieldExtension_code.setFieldName("code");
		fieldExtension_code.setStringValue("exxxxxx");
		Map<String, List<ExtensionAttribute>> attributes = new HashMap<String, List<ExtensionAttribute>>();
		List<FieldExtension> fieldExtensions = new ArrayList<FieldExtension>();
		fieldExtensions.add(fieldExtension_name);
		fieldExtensions.add(fieldExtension_code);
		activitiListener_end.setFieldExtensions(fieldExtensions );;
		
		taskListeners.add(activitiListener_end);
		
		task.setTaskListeners(taskListeners);

		return task;
	}

	public SequenceFlow getFlow(String id, String name, String startId, String endId) {
		SequenceFlow sequenceFlow = new SequenceFlow();
		sequenceFlow.setId(id);
		sequenceFlow.setName(name);
		sequenceFlow.setSourceRef(startId);
		sequenceFlow.setTargetRef(endId);

		return sequenceFlow;
	}

	private BpmnModel getModel(String processId) {
		BpmnModel model = new BpmnModel();
		// 流程
		Process process = getProcess(processId);
		
		//设置实例级的监听 20180727
		List<ActivitiListener> executionListeners = new ArrayList<ActivitiListener>();
		ActivitiListener executionListener = new ActivitiListener();
		executionListener.setEvent(ExecutionListener.EVENTNAME_START);
//		executionListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
//		executionListener.setImplementation("{"+WorkFlowParams.DELEGATEEXPRESSION+"}");
		
		executionListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
		executionListener.setImplementation("cn.cj.study.activiti.listener.Demo_listener");
		executionListeners.add(executionListener);
		process.setExecutionListeners(executionListeners);
		
		// 开始节点
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startId");
		startEvent.setName("开始节点");
		process.addFlowElement(startEvent);
		
		// 结束节点
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endId");
		endEvent.setName("结束节点");
		process.addFlowElement(endEvent);
		
		//任务
		UserTask userTask =getTask();
		process.addFlowElement(userTask);
		
		SequenceFlow flow1 = getFlow("flow1", "flow1", startEvent.getId(), userTask.getId());
		process.addFlowElement(flow1);
		SequenceFlow flow2 = getFlow("flow2", "flow2", userTask.getId(), endEvent.getId());
		process.addFlowElement(flow2);
		
		model.addProcess(process);

		return model;
	}

}
