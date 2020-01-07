package com.fu.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 *  通知类
 * @Aspect 告诉Spring当前类是一个切面类
 */
//@Aspect
//@Component
public class LogAspectsConfig {

    @Pointcut("execution(* com.fu.service..*(..))")
    public void pointCut() {}

    /**
     * JoinPoint joinPoint 连接点
     *        JoinPoint 一定要出现在参数表的第一位，否则spring识别不出来，会抛出异常
     */
    @Before("pointCut()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("logBefore..."+ joinPoint.getSignature().getName() +"方法运行...参数为：" + Arrays.toString(args));
    }

    @After("pointCut()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("logAfter..."+methodName+"方法运行结束...");
    }

    /**
        value = "pointCut()" 切点
        returning = "result" 返回方法运行的结果，使用一个参数(result)接收，参数和方法参数名对应
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("logAfterReturning..."+ methodName +"正常返回，返回值是：" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("logAfterThrowing..."+ methodName +"方法出现异常，异常信息为：" + throwable);
    }

    /**
        环绕通知和异常通知同时使用，如果使用 try{}catch(){} 方式捕获异常，
            则需要把异常信息重新抛出，否则异常通知不生效（捕获不到异常）
     */
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        System.out.println("logAround...Before...");
        result = point.proceed();
        System.out.println("logAround...After...result = " + result);
        return result;
    }
}
		/*  无异常
            ------spring容器启动成功---------
            logAround...Before...
            logBefore...division方法运行...参数为：{[1, 1]}
            MathCalculator.....division...
            logAround...After...result = 1
            logAfter...division方法运行结束...
            logAfterReturning...division正常返回，返回值是：1
         */
        /*  有异常
            ------spring容器启动成功---------
            logAround...Before...
            logBefore...division方法运行...参数为：{[1, 0]}
            MathCalculator.....division...
            logAfter...division方法运行结束...
            logAfterThrowing...division方法出现异常，异常信息为：java.lang.ArithmeticException: / by zero
         */