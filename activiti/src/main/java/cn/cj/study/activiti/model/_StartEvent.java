package cn.cj.study.activiti.model;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.ErrorEventDefinition;
import org.activiti.bpmn.model.EventDefinition;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.MessageEventDefinition;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.TimerEventDefinition;

/**
 * 启动节点
 * 
 * @author jun.chen
 *
 */
public class _StartEvent {

	public StartEvent getEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEventId");
		startEvent.setName("startEventName");
		// 设置操作人Id
		startEvent.setInitiator("startUserId");
		// 设置启动的关联表单（外置表单）
		startEvent.setFormKey("formKey");
		// 设置动态表单
		List<FormProperty> formProperties = new ArrayList<FormProperty>();
		FormProperty property = new FormProperty();
		property.setId("propertyId");
		property.setName("propertyName");
		property.setType("type");
		formProperties.add(property);
		startEvent.setFormProperties(formProperties);
		// 定时启动事件
		List<EventDefinition> eventDefinitions = new ArrayList<EventDefinition>();
		/** 设置定时事件 */
		TimerEventDefinition timerEventDefinition = new TimerEventDefinition();
		timerEventDefinition.setTimeDate("2017-11-19");// 指定日期
		// timerEventDefinition.setTimeDuration("PT10M");// 设置多久后启动
		// timerEventDefinition.setTimeCycle("R3/PT10H");// 多久执行一次
		eventDefinitions.add(timerEventDefinition);
		/** 异常启动事件 */
		ErrorEventDefinition errorEventDefinition = new ErrorEventDefinition();
		errorEventDefinition.setErrorCode("ErrorCode");
		eventDefinitions.add(errorEventDefinition);
		/**消息启动*/
		MessageEventDefinition messageEventDefinition =new MessageEventDefinition();
		messageEventDefinition.setMessageRef("t_name:messageId");//消息的Id需要有bpmnModel的TaegetNameSpace
		eventDefinitions.add(messageEventDefinition);
		
		startEvent.setEventDefinitions(eventDefinitions);
		
		return startEvent;
	}

}
