package com.test.aop.traditional;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

//自定义环绕Advice，实现对应的接口，记录运行时间
public class TimeLogAdvice implements MethodInterceptor {
	private static Logger logger = Logger.getLogger(TimeLogAdvice.class);
	
	//参数：目标方法回调函数的包装类，通过该类可获取调用方法的相关属性、方法名、调用该方法的对象等
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		long begin = System.currentTimeMillis();
		//目标对象原有方法执行
		Object object = methodInvocation.proceed();
		long end = System.currentTimeMillis();
		//记录日志，配置文件需要打开file
		long during = end - begin;
		logger.info(methodInvocation.getThis().getClass().getName() + "的" + methodInvocation.getMethod().getName() + "方法运行了" + during + "ms");
		//放行
		return object;
	}
}
