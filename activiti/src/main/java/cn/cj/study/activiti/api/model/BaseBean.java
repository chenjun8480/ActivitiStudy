package cn.cj.study.activiti.api.model;

import java.io.Serializable;
import java.util.List;

public class BaseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private Object[] args;

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	private List<TestBean> tt;

	public List<TestBean> getTt() {
		return tt;
	}

	public void setTt(List<TestBean> tt) {
		this.tt = tt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
