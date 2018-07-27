package cn.cj.study.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.aspectj.runtime.reflect.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.cj.study.activiti.api.model.BaseBean;
import cn.cj.study.activiti.api.model.TestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class TaskTest {
	String key = "demo.bpmn";
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
		String simpleActivitiXml = tdemo.getSimpleActivitiXml();
		System.out.println(simpleActivitiXml);
		repositoryService.createDeployment().addString(key, simpleActivitiXml).deploy();
	}

	@Test
	public void query() {
		DeploymentQuery query = repositoryService.createDeploymentQuery();
		List<Deployment> deployments = query.processDefinitionKey("processId").list();
		// List<ProcessDefinition> list =
		// repositoryService.createProcessDefinitionQuery().processDefinitionKey("processId").list();
		System.out.println(deployments.get(0).getId());
		repositoryService.deleteDeployment(deployments.get(0).getId());

	}

	@Test
	public void start() {
		ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("processId").singleResult();
		runtimeService.startProcessInstanceById(singleResult.getId());
	}

	@Test
	public void complete() {
		Task singleResult = taskService.createTaskQuery().processDefinitionKey("processId").singleResult();
		Map<String, Object> variables = new HashMap<String, Object>();
		BaseBean baseBean = new BaseBean();
		baseBean.setCode("123");
		baseBean.setId(null);
		List<TestBean> tt = new ArrayList<TestBean>();
		TestBean bean =new TestBean();
		bean.setCode("22");
		bean.setName("33");
		tt.add(bean);
		baseBean.setTt(tt );
		variables.put("test", baseBean);
		taskService.complete(singleResult.getId(), variables);
	}
	
	@Test
	public void queryHistory() {
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().list();
		System.out.println(list.size());
		
	}

}
