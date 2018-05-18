package com.test.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxyFactory {
	//成员变量-目标对象
	private Object target;
	
	//有参构造（不用有参构造传入目标对象，用setter也行）
	public CglibProxyFactory(Object target) {
		this.target = target;
	}
	
	//获取代理对象
	public Object getProxyInstance(){
		//1.代理对象生成器(工厂思想)
		Enhancer enhancer = new Enhancer();
		//2.在增强器上设置两个属性
		//设置目标对象 
		enhancer.setSuperclass(target.getClass());
		//设置回调方法
		enhancer.setCallback(new MethodInterceptor() {
			//参数1：代理对象；参数2：目标对象的方法对象；参数3：目标对象的方法实参；参数4：代理对象的方法对象
			@Override
			public Object intercept(Object proxy, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
				if ("save".equals(method.getName())) {
					System.out.println("记录日志...");
				}
				Object object = method.invoke(target, arg);
				return object;
			}
		});
		//3.返回代理对象
		return enhancer.create();
	}
}
