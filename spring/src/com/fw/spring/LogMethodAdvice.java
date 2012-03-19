package com.fw.spring;

import java.lang.reflect.Method;

import org.apache.log4j.NDC;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class LogMethodAdvice implements MethodBeforeAdvice,
        AfterReturningAdvice {

    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        if (NDC.getDepth() == 0) {
            NDC.push(target.getClass().getSimpleName());
        } else {
            NDC.push("-> " + target.getClass().getSimpleName());
        }
    }

    public void afterReturning(Object obj, Method method, Object[] args,
            Object target) throws Throwable {
        NDC.pop();
    }
}