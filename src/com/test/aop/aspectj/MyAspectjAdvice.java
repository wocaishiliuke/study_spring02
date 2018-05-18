package com.test.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//aspectJ通知类
public class MyAspectjAdvice {
	//aspectJ AOP编程方式
	public void firstBefore() {
		System.out.println("第1个前置通知...");
	}
	public void secondBefore() {
		System.out.println("第2个前置通知...");
	}
	
	//各种通知类型
	/**
	 * 1.（会被配置为）前置通知
	 * @param joinPoint 连接点对象
	 */
	public void before(JoinPoint joinPoint) {
		System.out.println("前置通知执行了...");
		//JoinPoint接口的API，获取连接点的相关信息
		System.out.println("连接点方法:" + joinPoint.getSignature());
		System.out.println("目标对象:" + joinPoint.getTarget().getClass().getName());
		System.out.println("代理对象:" + joinPoint.getThis().getClass().getName());
		//权限拦截
		String name = "rose";
		if (!"admin".equals(name)) {
			throw new RuntimeException("无权访问" + joinPoint.getTarget().getClass().getName() + "中的" + joinPoint.getSignature());
		}
	}
	
	/**
	 * 2.（会被配置为）后置通知
	 * @param joinPoint 连接点对象
	 * @param returnVal 目标方法执行后的返回值,类型是object，参数名任意，会配置到xml
	 */
	public void afterReturing(JoinPoint joinPoint, Object returnVal) {
		System.out.println("后置通知执行了...");
		System.out.println("尊敬的用户，您的余额为：" + returnVal);
	}
	
	/**
	 * 3.（会被配置为）环绕通知
	 * @param proceedingJoinPoint (执行中的)连接点对象
	 * @return 返回目标方法的返回值
	 * @throws Throwable 必须抛出Throwable
	 */
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("环绕通知执行了...");
		System.out.println("开启事务...");
		//调用proceed()方法可以随时随地执行目标对象的方法
		Object returnVal = proceedingJoinPoint.proceed();
		System.out.println("提交事物...");
		return returnVal;
	}
	
	/**
	 * 4.（会被配置为）抛出通知
	 * @param joinPoint 连接点对象
	 * @param ex 目标方法抛出的异常，类型是Throwable，参数名任意，会配置到xml
	 */
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		System.out.println("抛出通知执行了...");
		System.out.println("管理员" + joinPoint.getTarget().getClass().getName() + "的方法：" + joinPoint.getSignature().getName() + "发生了异常，异常为：" + ex.getMessage());
	}
	
	/**
	 * 5.（会被配置为）最终通知
	 * @param joinPoint 连接点对象
	 */
	public void after(JoinPoint joinPoint) {
		System.out.println("最终通知执行了...");
		System.out.println("数据库连接被释放了。执行的方法是：" + joinPoint.getSignature().getName());
	}
}