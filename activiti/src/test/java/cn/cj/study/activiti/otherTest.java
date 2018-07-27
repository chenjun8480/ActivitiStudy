package cn.cj.study.activiti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import cn.cj.study.activiti.api.model.BaseBean;

public class otherTest {

	@Test
	public void test() {
		BaseBean baseBean = new BaseBean();
		baseBean.setCode("a");

		File file = new File("D:\\test.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// Student对象序列化过程
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(baseBean);
			oos.flush();
			oos.close();
			fos.close();

			// Student对象反序列化过程
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BaseBean st1 = (BaseBean) ois.readObject();
			System.out.println("code = " + st1.getCode());
			ois.close();
			fis.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
