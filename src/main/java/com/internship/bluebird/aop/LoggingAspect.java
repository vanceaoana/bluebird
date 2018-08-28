package com.internship.bluebird.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
public class LoggingAspect {

    @Around("publicMethodInsideAClassMarkedWithAtRestController()")
    public Object aroundRestController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        LoggerFactory.getLogger(method.getDeclaringClass())
                .info("[RestController][endpointName: {}][httpMethod: {}][Duration: {}][queryParams: {}][URL: {}]",
                method.getName(),
                request.getMethod(),
                (System.currentTimeMillis() - startTime),
                request.getQueryString(),
                request.getRequestURL());

        return result;
    }

    @Around("publicMethodInsideAClassMarkedWithAtService()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        LoggerFactory.getLogger(method.getDeclaringClass())
                .info("[Service][method: {}][Duration: {}][params: {}]",
                        method.getName(),
                        (System.currentTimeMillis() - startTime),
                        getStringParamsValue(proceedingJoinPoint.getArgs()));

        return result;
    }

    @Around("publicMethodInsideAClassMarkedWithAtRepository()")
    public Object aroundRepository(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        LoggerFactory.getLogger(method.getDeclaringClass())
                .info("[Repository][method: {}][Duration: {}][params: {}]",
                        method.getName(),
                        (System.currentTimeMillis() - startTime),
                        getStringParamsValue(proceedingJoinPoint.getArgs()));

        return result;
    }

    @Pointcut("within(com.internship.bluebird.controller.*)")
    public void publicMethodInsideAClassMarkedWithAtRestController() {
    }

    @Pointcut("within(com.internship.bluebird.service.*)")
    public void publicMethodInsideAClassMarkedWithAtService() {
    }
    @Pointcut("within(com.internship.bluebird.repo.*)")
    public void publicMethodInsideAClassMarkedWithAtRepository() {
    }

    private String getStringParamsValue(Object[] params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object param : params) {
            if (param != null) {
                stringBuilder = stringBuilder.append(" ").append(param.toString());
            } else {
                stringBuilder.append(" ").append("null");
            }
        }
        return stringBuilder.toString();
    }
}
