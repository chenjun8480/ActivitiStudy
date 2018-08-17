package cn.cj.study.activiti.api.service;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 任务级别的监听<br/>
 * 任务创建、完成、签收或删除 甚至每次时触发
 * 
 * @author jun.chen
 *
 */
public interface IWorkFlowTaskListener extends TaskListener {
	
	/**
	 * 签收时相关
	 * @param delegateTask
	 */
	void onAssignment(DelegateTask delegateTask);
	
	/**
	 * 创建时相关
	 * @param delegateTask
	 */
	void onCreate(DelegateTask delegateTask);
	
	/**
	 * 完成时相关
	 * @param delegateTask
	 */
	void onComplete(DelegateTask delegateTask);
	
	/**
	 * 删除时相关
	 * @param delegateTask
	 */
	void onDelete(DelegateTask delegateTask);

}
