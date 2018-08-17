package cn.cj.study.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.cj.study.activiti.api.model.BaseBean;
import cn.cj.study.activiti.api.model.TestBean;
import cn.cj.study.activiti.common.CommentsType;
import cn.cj.study.activiti.common.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class TaskTest {
	String key = "demo_4.bpmn";
	String processId = "processId_4";
	@Autowired
	ProcessEngineFactoryBean processEngine;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;

	@Test
	public void testExpress() throws Exception {
		ActivitiDemoTest tdemo = new ActivitiDemoTest();
		String simpleActivitiXml = tdemo.getSimpleActivitiXml(processId);
		System.out.println(simpleActivitiXml);
		Deployment deploy = repositoryService.createDeployment().addString(key, simpleActivitiXml).deploy();
		System.out.println(deploy.getName());
	}

	@Test
	public void query() {
		DeploymentQuery query = repositoryService.createDeploymentQuery();
		List<Deployment> deployments = query.processDefinitionKey(processId).list();
		// List<ProcessDefinition> list =
		// repositoryService.createProcessDefinitionQuery().processDefinitionKey("processId").list();
		System.out.println(deployments.get(0).getId());
		// 取消发布
		// repositoryService.deleteDeployment(deployments.get(0).getId());

	}

	@Test
	public void start() {
		ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processId).singleResult();
		ProcessInstance instance = runtimeService.startProcessInstanceById(singleResult.getId());
		System.out.println(instance.getActivityId());
	}

	@Test
	public void queryTask() {
		TaskQuery taskQuery = taskService.createTaskQuery();
		int size = taskQuery.taskAssignee("jun.chen").list().size();
		System.out.println(size);
	}

	@Test
	public void complete() {
		List<Task> list = taskService.createTaskQuery().processDefinitionKey(processId).list();
		for (Task singleResult : list) {
			Map<String, Object> variables = new HashMap<String, Object>();
			BaseBean baseBean = new BaseBean();
			baseBean.setCode("123");
			baseBean.setId(null);
			List<TestBean> tt = new ArrayList<TestBean>();
			TestBean bean = new TestBean();
			bean.setCode("22");
			bean.setName("33");
			tt.add(bean);
			baseBean.setTt(tt);
			variables.put("test", baseBean);
			taskService.addComment(singleResult.getId(), singleResult.getProcessInstanceId(), CommentsType.SIMPLE,
					StringUtil.format("TaskId:{0},ProcessInstanceId{1} 完成时批注。", singleResult.getId(),
							singleResult.getProcessInstanceId()));
			taskService.complete(singleResult.getId(), variables);
		}
	}
	
	//查询批注
	@Test
	public void queryComment() {
		List<Comment> taskComments = taskService.getTaskComments("55004", CommentsType.SIMPLE);
		taskService.getCommentsByType(CommentsType.SIMPLE);
		System.out.println(taskComments.get(0).getFullMessage());
	}

	@Test
	public void queryHistory() {
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().list();
		System.out.println(list.size());

	}

}
