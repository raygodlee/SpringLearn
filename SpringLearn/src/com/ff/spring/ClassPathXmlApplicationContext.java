package com.ff.spring;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ClassPathXmlApplicationContext implements BeanFactory{
	private Map<String, Object> beans=new HashMap<String,Object>();
	
	public ClassPathXmlApplicationContext() throws JDOMException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		SAXBuilder sb=new SAXBuilder();
		Document doc=sb.build(this.getClass().getClassLoader().getResourceAsStream("beans.xml"));
		Element root=doc.getRootElement();
		List<Element> list=root.getChildren("bean");
		for (int i = 0; i < list.size(); i++) {
			Element element=list.get(i);
			String id = element.getAttributeValue("id");
			String clazz=element.getAttributeValue("class");
			System.out.println(id+":"+clazz);
			Object o=Class.forName(clazz).newInstance();
			beans.put(id, o);
		}
		for (int i = 0; i < list.size(); i++) {
			Element element=list.get(i);
			Object o=beans.get(element.getAttributeValue("id"));
			for (Element element2:element.getChildren()) {
				String name=element2.getAttributeValue("name");
				String bean=element2.getAttributeValue("bean");
				Object beanObject=beans.get(bean);
				String methodName="set"+name.substring(0,1).toUpperCase()+name.substring(1);
				Method m=o.getClass().getMethod(methodName,beanObject.getClass().getInterfaces()[0]);
				m.invoke(o, beanObject);
			}
		}
	}
	@Override
	public Object getBean(String name) {
		// TODO Auto-generated method stub
		return beans.get(name);
	}

}
