package cn.cj.study.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.TaskListener;

import cn.cj.study.activiti.model._EndEvent;
import cn.cj.study.activiti.model._StartEvent;

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
	public String getSimpleActivitiXml() {
		BpmnModel model = getModel();
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
		byte[] convertToXML = bpmnXMLConverter.convertToXML(model);
		return new String(convertToXML);
	}

	private Process getProcess() {
		Process process = new Process();
		process.setId("processId");
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

	private BpmnModel getModel() {
		BpmnModel model = new BpmnModel();
		// 流程
		Process process = getProcess();
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
