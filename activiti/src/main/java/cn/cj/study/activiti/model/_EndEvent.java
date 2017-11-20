package cn.cj.study.activiti.model;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.CancelEventDefinition;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ErrorEventDefinition;
import org.activiti.bpmn.model.EventDefinition;
import org.activiti.bpmn.model.TerminateEventDefinition;

/**
 * 结束节点
 * 
 * @author jun.chen
 *
 */
public class _EndEvent {
	public EndEvent getEvent() {
		EndEvent endEvent = new EndEvent();
		// 空结束事件
		endEvent.setId("endEventId");
		endEvent.setName("endEventName");
		// 异常结束
		List<EventDefinition> eventDefinitions = new ArrayList<EventDefinition>();
		/** 异常结束定义 */
		ErrorEventDefinition errorEventDefinition = new ErrorEventDefinition();
		errorEventDefinition.setErrorCode("errorCode");
		eventDefinitions.add(errorEventDefinition);
		/** 终止的结束事件 */
		TerminateEventDefinition terminateEventDefinition = new TerminateEventDefinition();
		eventDefinitions.add(terminateEventDefinition);
		// 取消的结束事件（只能在子流程使用）可以取消一个事务子流程的执行
		CancelEventDefinition cancelEventDefinition = new CancelEventDefinition();
		eventDefinitions.add(cancelEventDefinition);
		
		endEvent.setEventDefinitions(eventDefinitions);

		return endEvent;
	}

}
