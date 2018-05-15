package cn.anx.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {
    @Pointcut("execution(* cn.anx.pojo.*.*(..))")
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
