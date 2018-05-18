package com.test.proxy.cglib;

import org.junit.Test;

public class SpringTest {
	@Test
	public void testCglibProxy(){
		//目标对象
		ProductServiceImpl target = new ProductServiceImpl();
		//代理工厂对象，注入目标
		CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(target);
		//织入，生成代理对象
		ProductServiceImpl proxy=(ProductServiceImpl) cglibProxyFactory.getProxyInstance();
		//调用代理对象的方法
		proxy.save();
		System.out.println("-----------------------------");
		proxy.find();
	}
}
