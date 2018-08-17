package cn.cj.study.activiti.listener.core;

import java.util.Date;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

import cn.cj.study.activiti.api.service.IWorkFlowTaskListener;
import cn.cj.study.activiti.common.util.StringUtil;

public abstract class AbstractWorkFlowTaskListener implements IWorkFlowTaskListener {

	private static final long serialVersionUID = 1L;
	
	private Expression code;
	private Expression name;

	public void notify(DelegateTask delegateTask) {

		System.out.println(delegateTask.getCreateTime());
		// 当前任务进行的节点
		String eventName = delegateTask.getEventName();

		String userName = delegateTask.getAssignee();
		String taskId = delegateTask.getId();
		String taskName = delegateTask.getName();
		Date createTime = delegateTask.getCreateTime();
		Date dueDate = delegateTask.getDueDate();
		
		System.out.println(code.getExpressionText());
		System.out.println(name.getExpressionText());
		if (eventName.equals(TaskListener.EVENTNAME_ASSIGNMENT)) {
			// 签收任务时
			System.out.println(StringUtil.format("任务[{0}]{1} 已经被 用户{2} 签收。 时间:{3}-{4}", taskId, taskName, userName,
					createTime, dueDate));
			onAssignment(delegateTask);

		} else if (eventName.equals(TaskListener.EVENTNAME_CREATE)) {
			// 创建任务时
			System.out.println(
					StringUtil.format("任务[{0}]{1} 已经被创建。 时间:{3}-{4}", taskId, taskName, userName, createTime, dueDate));
			onCreate(delegateTask);
		} else if (eventName.equals(TaskListener.EVENTNAME_COMPLETE)) {
			// 任务完成
			System.out.println(
					StringUtil.format("任务[{0}]{1} 已经完成。 时间:{3}-{4}", taskId, taskName, userName, createTime, dueDate));
			onComplete(delegateTask);
		} else if (eventName.equals(TaskListener.EVENTNAME_DELETE)) {
			// 任务删除
			System.out.println(
					StringUtil.format("任务[{0}]{1} 已经被删除。 时间:{3}-{4}", taskId, taskName, userName, createTime, dueDate));
			onDelete(delegateTask);
		}
	}

	public void onAssignment(DelegateTask delegateTask) {
		System.out.println("签收时的额外处理！");
	}

	public void onCreate(DelegateTask delegateTask) {
		System.out.println("创建时的额外处理！");
	}

	public void onComplete(DelegateTask delegateTask) {
		System.out.println("完成时的额外处理！");
	}

	public void onDelete(DelegateTask delegateTask) {
		System.out.println("删除时的额外处理！");
	}

}
