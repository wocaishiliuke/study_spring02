package com.test.aop.aspectj.anno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-anno.xml")
public class SpringTest {
	@Autowired
	private CustomerService customerService;//有接口的target实例
	@Autowired
	private ProductServiceImpl productService;//无接口的target实例
	
	@Test
	public void test(){
		//基于接口
		//customerService.save();
		//customerService.find();
		//customerService是CustomerService类型的引用，多态，要调用实现类特有的方法，就要向下强转
		//但customerService引用指向的，是JDK动态代理对象，该对象是接口的子类型的对象，和CustomerServiceImpl是兄弟关系
		((CustomerServiceImpl) customerService).code();
		System.out.println("-------------");
		//基于类的
		productService.save();
		productService.find();
	}
}
