package com.test.proxy.jdk;

import org.junit.Test;
//使用JDK动态代理，对原来的方法进行功能增强，而无需更改原来的代码
public class SpringTest {
	@Test
	public void testJdkProxy(){
		//目标对象
		CustomerService target = new CustomerServiceImpl();
		//创建代理工厂实例，有参构造注入目标对象
		JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(target);
		//获取代理对象
		CustomerService proxy = (CustomerService)jdkProxyFactory.getProxyInstance();
		//调用目标对象的方法
		proxy.save();
		System.out.println("-----------------------------");
		proxy.find();
	}
}
