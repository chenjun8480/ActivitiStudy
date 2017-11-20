package cn.cj.study.activiti.model;

import org.activiti.bpmn.model.Message;

/**
 * 消息
 * 
 * @author jun.chen
 *
 */
public class _Message {

	public Message getMessage() {
		Message message = new Message();
		message.setId("messageId");
		message.setName("发送的消息");
		return message;
	}

}
