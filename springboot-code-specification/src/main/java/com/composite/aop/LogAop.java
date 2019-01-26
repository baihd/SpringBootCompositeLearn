package com.composite.aop;

import com.alibaba.fastjson.JSONObject;
import com.composite.annotations.Log;
import com.composite.utils.SPELUtil;
import com.composite.utils.UserUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


//相当于Spring上中的<aop:aspect id="logAop" ref="LogAop">
@Aspect
@Component
@Slf4j
public class LogAop {

    public static final String JSON_KEY = "logjson";

    //定义一个公用方法
    @Pointcut("@annotation(com.composite.annotations.Log))")
    public void logPointcut() {
    }

    //相当于<aop:before method="handlerLogMethod" pointcut-ref="logPointcut()" />
    @Around("logPointcut()")
    @SneakyThrows
    public Object handlerLogMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            putLogInfo2MDC(pjp);
            result = pjp.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } finally {
            clearMDC();
        }
        return result;
    }

    private void putLogInfo2MDC(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);
        SPELUtil spelUtil = new SPELUtil(pjp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("U", UserUtil.getUserIfLogin());
        jsonObject.put("A", logAnnotation.action());
        jsonObject.put("T", logAnnotation.itemType());
        jsonObject.put("I", spelUtil.cacl(logAnnotation.itemId()));
        jsonObject.put("P", spelUtil.cacl(logAnnotation.param()));
        MDC.put(JSON_KEY, jsonObject.toJSONString());
    }

    private void clearMDC() {
        MDC.remove(JSON_KEY);
    }

}
