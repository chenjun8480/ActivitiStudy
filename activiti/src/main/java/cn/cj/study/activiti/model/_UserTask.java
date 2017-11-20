package cn.cj.study.activiti.model;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 用户任务
 * 
 * @author jun.chen
 *
 */
public class _UserTask {
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
		// 设置到期日
		task.setDueDate("${overDate}");
		// 设置优先级（范围[0,100]）
		task.setPriority("2");
		// 添加监听
		List<ActivitiListener> taskListeners = new ArrayList<ActivitiListener>();
		ActivitiListener activitiListener = new ActivitiListener();
		/** 设置监听选项 create-创建任务 assignment-分配任务 complete-完成任务 delete-删除任务 */
		activitiListener.setEvent(TaskListener.EVENTNAME_COMPLETE);
		/** 指定处理监听的方式 Java的全路径类-class 表达式方式-express、delegateExpression */
		activitiListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
		activitiListener.setImplementation("com.cn.implementation");
		taskListeners.add(activitiListener);

		task.setTaskListeners(taskListeners);

		return task;
	}

}
