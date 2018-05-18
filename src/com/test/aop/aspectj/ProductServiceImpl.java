package com.test.aop.aspectj;

public class ProductServiceImpl {
	public void save() {
		int i = 1/ 0;
		System.out.println("商品保存了...");
	}

	public int find() {
		System.out.println("商品查询了...");
		return 22;
	}
}