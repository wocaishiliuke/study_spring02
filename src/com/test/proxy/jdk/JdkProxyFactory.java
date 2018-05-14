package com.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory implements InvocationHandler {
	//成员变量-目标对象
	private Object target;
	
	//有参构造（不用有参构造传入目标对象，用setter也行）
	public JdkProxyFactory(Object target) {
		this.target = target;
	}
	
	//提供获取代理对象的方法
	//方案一：创建InvocationHandler接口的匿名内部类
	/*public Object getProxyInstance() {
		//参数1：目标对象的类加载器；参数2：目标对象实现的接口；参数3：回调方法对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			//proxy：代理对象；method：代理对象中与目标对象对应的方法(对象)；args：方法参数
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				if ("save".equals(method.getName())) {
					System.out.println("记录日志...");//日志记录增强
				}
				Object object = method.invoke(target, args);//目标对象的原有方法，并且返回返回值
				return object;
			}
		});
	}*/
	
	//方案二：不使用匿名内部类，自定义InvocationHandler接口内部实现类
	/*public Object getProxyInstance() {
		//参数1：目标对象的类加载器；参数2：目标对象实现的接口；参数3：回调方法对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new MyInvocationHandler());
	}
	//自定义接口实现的内部类，代替匿名内部类（private不能修饰类，但可以修饰内部类，相当于成员变量）
	private class MyInvocationHandler implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if ("save".equals(method.getName())) {
				System.out.println("记录日志...");//日志记录增强
			}
			Object object = method.invoke(target, args);//目标对象的原有方法，并且返回返回值
			return object;
		}
	}*/
	
	//方案三：本工厂类自己实现InvocationHandler接口
	public Object getProxyInstance() {
		//参数1：目标对象的类加载器；参数2：目标对象实现的接口；参数3：回调方法对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ("save".equals(method.getName())) {
			System.out.println("记录日志...");//日志记录增强
		}
		Object object = method.invoke(target, args);//目标对象的原有方法，并且返回返回值
		return object;
	}
}
