package cn.cj.study.activiti.model;

import org.activiti.bpmn.model.ScriptTask;
import org.activiti.engine.impl.scripting.ScriptingEngines;

/**
 * 脚本任务<br/>
 * 支持Groovy、Javascript、Juel
 * 
 * @author jun.chen
 *
 */
public class _ScriptTask {
	public ScriptTask getTask() {
		ScriptTask task = new ScriptTask();
		task.setId("scriptTaskId");
		task.setName("scriptTaskName");
		// 设置脚本类型
		task.setScriptFormat(ScriptingEngines.GROOVY_SCRIPTING_LANGUAGE);
		task.setResultVariable("name");
		String script = "<![CDATA[ def name = \\\"jun.chen\\\"; execution.setVariable('name',name);]]>";
		task.setAutoStoreVariables(true);
		task.setScript(script);
		
		return task;
	}
}
