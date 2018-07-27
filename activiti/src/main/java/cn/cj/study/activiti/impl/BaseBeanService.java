package cn.cj.study.activiti.impl;

import cn.cj.study.activiti.api.model.BaseBean;
import cn.cj.study.activiti.api.service.IBaseBeanService;

public class BaseBeanService implements IBaseBeanService<BaseBean> {

	public String showMessage(BaseBean t) {
		String str = t.toString();
		return str;
	}

	public void showTest() {
		System.out.println("testShow");
	}

}
