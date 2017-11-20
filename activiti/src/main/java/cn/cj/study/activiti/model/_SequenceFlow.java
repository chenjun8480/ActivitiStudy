package cn.cj.study.activiti.model;

import org.activiti.bpmn.model.SequenceFlow;

/**
 * 连线（顺序流）
 * 
 * @author jun.chen
 *
 */
public class _SequenceFlow {
	public SequenceFlow getFlow() {
		SequenceFlow sequenceFlow = new SequenceFlow();
		//标准顺序流
		sequenceFlow.setId("flowId");
		sequenceFlow.setName("flowName");
		sequenceFlow.setSourceRef("startEventId");
		sequenceFlow.setTargetRef("endEventId");
		//条件顺序流
		sequenceFlow.setConditionExpression("${isPass}");
		
		return sequenceFlow;
	}
}
