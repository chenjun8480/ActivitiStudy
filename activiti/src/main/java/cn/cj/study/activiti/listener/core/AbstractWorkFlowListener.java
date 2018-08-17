package cn.cj.study.activiti.listener.core;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import cn.cj.study.activiti.api.service.IWorkFlowListener;

public abstract class AbstractWorkFlowListener implements IWorkFlowListener {

	private static final long serialVersionUID = 1L;

	public void notify(DelegateExecution execution) throws Exception {
		String eventName = execution.getEventName();

		if (eventName.equals(ExecutionListener.EVENTNAME_START)) {
			// 流程开始时
			onStart(execution);
		} else if (eventName.equals(ExecutionListener.EVENTNAME_END)) {
			// 流程结束时
			onEnd(execution);
		}

	}

	public void onStart(DelegateExecution execution) {
		System.out.println("开始时的额外处理！");		
	}

	public void onEnd(DelegateExecution execution) {
		System.out.println("结束时的额外处理！");		
	}

	
}
