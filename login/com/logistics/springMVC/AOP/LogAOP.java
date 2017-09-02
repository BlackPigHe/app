package com.logistics.springMVC.AOP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.pojo.User;
import com.logistics.util.AopUtil;
import com.logistics.util.UserUtil;

//声明这是一个组件
@Component
// 声明这是一个切面Bean
@Aspect
public class LogAOP {

	@Pointcut("execution(* com.logistics.springMVC.controller.*.*(..))")
	public void myMethod() {
	};

	@Pointcut("execution(* com.logistics.springMVC.controller.*.*(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,..))")
	public void login() {
	};

	@Around(value = "login()")
	/*
	 * 
	 * 
	 * */
	public Object login(ProceedingJoinPoint pjp) throws IOException {
		
		Object[] args = pjp.getArgs();
		AopUtil util=new AopUtil();
		User user = null;
		System.out.println("mode");
		Map webMap=util.getWeb(args);
		HttpServletRequest req=(HttpServletRequest)webMap.get("request");
		HttpServletResponse resp=(HttpServletResponse) webMap.get("response");
		String mothName = pjp.getSignature().toString();
		user=UserUtil.getLoginUtil(req);
		System.out.println(user);
		if (user == null){
			if (mothName.indexOf("login") == -1) {
				System.out.println(req.getContextPath());
				resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
				return null;
			}
		}
		Object res = null;
		try {
			res = pjp.proceed(args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("这里");
		return res;
	}

	// @Around(value = "myMethod()")
	// public Object openLog(ProceedingJoinPoint pjp) {
	// Object rest = null;
	// Object[] objs = pjp.getArgs();
	// for (Object o : objs) {
	// if (o instanceof HttpServletRequest) {
	// HttpServletRequest req = (HttpServletRequest) o;
	// User user = UserUtil.getLoginUtil(req);
	//
	// }
	// }
	// try {
	// rest = pjp.proceed();// 执行方法
	// System.out.println("后通知=" + rest);
	// } catch (Throwable e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return rest;
	//
	// }
}
