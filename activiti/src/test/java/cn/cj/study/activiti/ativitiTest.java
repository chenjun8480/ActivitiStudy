package cn.cj.study.activiti;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Message;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.ScriptTask;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.db.DbSchemaCreate;
import org.activiti.engine.impl.db.DbSchemaDrop;
import org.junit.Test;

import cn.cj.study.activiti.model._EndEvent;
import cn.cj.study.activiti.model._Message;
import cn.cj.study.activiti.model._ScriptTask;
import cn.cj.study.activiti.model._SequenceFlow;
import cn.cj.study.activiti.model._ServiceTask;
import cn.cj.study.activiti.model._StartEvent;
import cn.cj.study.activiti.model._UserTask;

public class ativitiTest {

	@Test
	public void createDBTable() {
		DbSchemaCreate.main(new String[] {});
	}

	@Test
	public void dropDBTable() {
		DbSchemaDrop.main(new String[] {});
	}

	private Process getProcess() {
		Process process = new Process();
		process.setId("processId");
		process.setName("processName");
		return process;
	}

	private StartEvent getStartEvent() {
		_StartEvent startEvent = new _StartEvent();
		return startEvent.getEvent();
	}

	private EndEvent getEndEvent() {
		_EndEvent endEvent = new _EndEvent();
		return endEvent.getEvent();
	}

	private SequenceFlow getSequenceFlow() {
		_SequenceFlow flow = new _SequenceFlow();
		return flow.getFlow();
	}

	private Message getMessage() {
		_Message message = new _Message();
		return message.getMessage();
	}

	private UserTask getUserTask() {
		_UserTask task = new _UserTask();
		return task.getTask();
	}

	private ScriptTask getScriptTask() {
		_ScriptTask task = new _ScriptTask();
		return task.getTask();
	}
	
	private ServiceTask getServiceTask() {
		_ServiceTask task = new _ServiceTask();
		return task.getTask();
	}
	
	private ServiceTask getWebServiceTask() {
		_ServiceTask task = new _ServiceTask();
		return task.getWebServiceTask();
	}
	
	
	@Test
	public void bpmnXml() {
		BpmnModel model = new BpmnModel();
		model.setTargetNamespace("t_name");
		// 消息
		Message message = getMessage();
		model.addMessage(message);
		// 流程
		Process process = getProcess();
		// 开始节点
		StartEvent startEvent = getStartEvent();
		process.addFlowElement(startEvent);
		// 结束节点
		EndEvent endEvent = getEndEvent();
		process.addFlowElement(endEvent);
		// 顺序流
		SequenceFlow flow = getSequenceFlow();
		process.addFlowElement(flow);
		//用户任务
		UserTask userTask = getUserTask();
		process.addFlowElement(userTask);
		//脚本任务
		ScriptTask scriptTask = getScriptTask();
		process.addFlowElement(scriptTask);
		// Java Service任务
		ServiceTask serviceTask = getServiceTask();
		process.addFlowElement(serviceTask);
		//Web Service任务
		ServiceTask webServiceTask = getWebServiceTask();
		process.addFlowElement(webServiceTask);
		//业务规则任务
		
		
		model.addProcess(process);
		showXML(model);
	}

	private void showXML(BpmnModel model) {
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
		byte[] convertToXML = bpmnXMLConverter.convertToXML(model);
		String bytes = new String(convertToXML);
		System.out.println(bytes);
	}

}
