package com.composite.aop;

import com.composite.beans.ResultBean;
import com.composite.exception.CheckException;
import com.composite.exception.NoPermissionException;
import com.composite.exception.UnLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 处理和包装异常
 */
//相当于Spring上中的<aop:aspect id="controllerAop" ref="ControllerAop">
@Aspect
@Component
public class ControllerAop {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    //定义一个公用方法
    @Pointcut("execution(public com.composite.beans.ResultBean *(..))")
    public void controllerPointcut() {
    }

    //相当于<aop:before method="handlerControllerMethod" pointcut-ref="controllerPointcut()" />
    @Around("controllerPointcut()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> resultBean;
        try {
            resultBean = (ResultBean<?>) pjp.proceed();
            Object[] args = pjp.getArgs();
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } catch (Throwable throwable) {
            resultBean = handlerException(pjp, throwable);
        }
        return resultBean;
    }


    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> resultBean = new ResultBean();
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            resultBean.setMsg(e.getLocalizedMessage());
            resultBean.setCode(ResultBean.CHECK_FAIL);
        } else if (e instanceof UnLoginException) {
            resultBean.setMsg(e.getLocalizedMessage());
            resultBean.setCode(ResultBean.NO_LOGIN);
        } else if (e instanceof NoPermissionException) {
            resultBean.setMsg(e.getLocalizedMessage());
            resultBean.setCode(ResultBean.NO_PERMISSION);
        } else {
            logger.error(pjp.getSignature() + "error", e);
            resultBean.setMsg(e.toString());
            resultBean.setCode(ResultBean.UNKNOWN_EXCEPTION);
        }
        return resultBean;
    }
}
