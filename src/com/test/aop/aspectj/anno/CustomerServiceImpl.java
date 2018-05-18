package com.test.aop.aspectj.anno;

import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	public void save() {
		System.out.println("客户保存了...");
	}

	public int find() {
		System.out.println("客户查询了...");
		return 100;
	}
	
	//实现类特有的方法，接口中没有定义
	public void code() {
		System.out.println("客户敲代码了...");
	}
}