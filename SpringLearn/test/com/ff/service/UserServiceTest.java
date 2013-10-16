package com.ff.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Provider.Service;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

import com.ff.dao.UserDAO;
import com.ff.dao.impl.UserDAOImpl;
import com.ff.model.User;
import com.ff.spring.BeanFactory;
import com.ff.spring.ClassPathXmlApplicationContext;

public class UserServiceTest {

	 @Test
	public void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException, JDOMException, IOException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		 BeanFactory factory = new ClassPathXmlApplicationContext();
//		 UserDAO userDAO=(UserDAOImpl)factory.getBean("u");
		 UserService service=(UserService)factory.getBean("userService");
//		 service.setUserDAO(userDAO);
		 User u=new User();
		 service.addUser(u);
	}
//	 @Test
	public void testXml(){
		 SAXBuilder sb = new SAXBuilder();
			Document doc=null;
			try {
				doc = sb.build(UserServiceTest.class.getClassLoader()
						.getResourceAsStream("test.xml"));
			} catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 构造文档对象
			Element root = doc.getRootElement(); // 获取根元素
			List list = root.getChildren("disk");// 取名字为disk的所有元素
			for (int i = 0; i < list.size(); i++) {
				Element element = (Element) list.get(i);
				String name = element.getAttributeValue("name");
				String capacity = element.getChildText("capacity");// 取disk子元素capacity的内容
				String directories = element.getChildText("directories");
				String files = element.getChildText("files");
				System.out.println("磁盘信息:");
				System.out.println("分区盘符:" + name);
				System.out.println("分区容量:" + capacity);
				System.out.println("目录数:" + directories);
				System.out.println("文件数:" + files);
				System.out.println("-----------------------------------");

			}
	}
}
