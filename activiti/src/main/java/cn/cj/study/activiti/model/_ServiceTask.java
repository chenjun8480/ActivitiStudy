package cn.cj.study.activiti.model;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.DataAssociation;
import org.activiti.bpmn.model.DataSpec;
import org.activiti.bpmn.model.IOSpecification;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.ServiceTask;

/**
 * Java Service任务
 * 
 * @author jun.chen
 *
 */
public class _ServiceTask {

	public ServiceTask getTask() {
		ServiceTask task = new ServiceTask();
		task.setId("serviceTaskId");
		task.setName("serviceTaskName");
		// 指定实现类型
		task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
		task.setImplementation("cn.cj.acticiti");

		// task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION);
		// task.setImplementation("#{service.back()}");
		// task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
		// task.setImplementation("${backDelegate}");

		// 将表达式expression的执行结果存储到制定的变量中
		task.setResultVariableName("backData");
		return task;
	}

	/**
	 * Web Service任务
	 * 
	 * @return
	 */
	public ServiceTask getWebServiceTask() {
		ServiceTask task = new ServiceTask();
		task.setId("webServiceId");
		task.setName("webServiceName");
		// 设置为WebService
		task.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);// 制定是class
		task.setImplementation(ImplementationType.IMPLEMENTATION_TYPE_WEBSERVICE);
//定义元素输入输出参数
		IOSpecification ioSpecification = new IOSpecification();
		List<DataSpec> dataInputs = new ArrayList<DataSpec>();
		ioSpecification.setDataInputs(dataInputs);
		List<String> dataInputRefs=new ArrayList<String>();
		dataInputRefs.add("ddTask");
		ioSpecification.setDataInputRefs(dataInputRefs);
		task.setIoSpecification(ioSpecification);
		//数据的输入关系
		List<DataAssociation> dataInputAssociations = new ArrayList<DataAssociation>();
		DataAssociation dataAssociation=new DataAssociation();
		dataAssociation.setSourceRef("sourceRef");
		dataAssociation.setTargetRef("targetRef");
		dataInputAssociations.add(dataAssociation);
		task.setDataInputAssociations(dataInputAssociations);
		//数据的输出关系
		List<DataAssociation> dataOutputAssociations = new ArrayList<DataAssociation>();
		task.setDataOutputAssociations(dataOutputAssociations);
		return task;
	}

}
