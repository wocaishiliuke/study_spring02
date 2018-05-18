package com.test.aop.traditional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-traditional.xml")
public class SpringTest {
	@Autowired
	private CustomerService customerService;//有接口的target实例
	@Autowired
	private ProductServiceImpl productService;//无接口的target实例
	
	@Test
	public void test(){
		//基于接口
		customerService.save();
		customerService.find();
		System.out.println("-------------");
		//基于类的
		productService.save();
		productService.find();
	}
}
