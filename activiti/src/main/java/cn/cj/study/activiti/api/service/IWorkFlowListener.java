package cn.cj.study.activiti.api.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 流程实例级别的监听器<br/>
 * 流程 开始、结束或认领时会触发
 * 
 * @author jun.chen
 *
 */
public interface IWorkFlowListener extends ExecutionListener {
	/**
	 * 流程开始时
	 * @param execution
	 */
	void onStart(DelegateExecution execution);
	
	/**
	 * 流程结束时
	 * @param execution
	 */
	void onEnd(DelegateExecution execution);

}
