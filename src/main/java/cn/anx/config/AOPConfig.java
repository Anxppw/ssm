package cn.anx.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPConfig {
    @Pointcut("execution(* cn.anx.service.impl.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before..."+joinPoint.getSignature().getName());
    }

    @After("pointcut()")
    public void after(){
        System.out.println("after...");
    }
}
