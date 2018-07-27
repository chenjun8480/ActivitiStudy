package cn.cj.study.activiti;

import static org.junit.Assert.assertNotNull;

import org.activiti.engine.RuntimeService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cj.study.activiti.api.service.IBaseBeanService;

public class StringActivitiTest {
	
	@Test
	public void createEngineUseSpring() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
//		ProcessEngineFactoryBean engineFactoryBean = ctx.getBean(ProcessEngineFactoryBean.class);
//		assertNotNull(engineFactoryBean);
//		RuntimeService runtimeService = ctx.getBean(RuntimeService.class);
//		assertNotNull(runtimeService);
		IBaseBeanService service = (IBaseBeanService) ctx.getBean("IBaseBeanService");
		service.showTest();
	}
}
