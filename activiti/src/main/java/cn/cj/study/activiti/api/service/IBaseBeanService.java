package cn.cj.study.activiti.api.service;

import cn.cj.study.activiti.api.model.BaseBean;

public interface IBaseBeanService<T extends BaseBean> {
	String showMessage(T t);
	
	void showTest();

}
