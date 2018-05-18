package com.test.aop.traditional;

public class ProductServiceImpl {
	public void save() {
		System.out.println("商品保存了...");
	}

	public int find() {
		System.out.println("商品查询了...");
		return 22;
	}
}