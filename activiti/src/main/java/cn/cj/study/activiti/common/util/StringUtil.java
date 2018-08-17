package cn.cj.study.activiti.common.util;

import java.text.MessageFormat;

/**
 * 字符串格式化工具类
 * 
 * @author jun.chen
 *
 */
public final class StringUtil {

	/**
	 * 格式化字符串
	 * 
	 * @param value
	 *            字符串中可以存在变量占位符{0}...{n}
	 * @param paras
	 *            对应占位符的变量值
	 * @return
	 */
	public static String format(String value, Object... paras) {
		return MessageFormat.format(value, paras);
	}

}
