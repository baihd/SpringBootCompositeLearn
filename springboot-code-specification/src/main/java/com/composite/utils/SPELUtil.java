package com.composite.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spring表达式AOP处理工具类
 */
public class SPELUtil {
    private final SpelExpressionParser parser;
    private final StandardEvaluationContext context;

    public SPELUtil(ProceedingJoinPoint pjp) {
        this.parser = new SpelExpressionParser();
        this.context = new StandardEvaluationContext();
        extractArgments(pjp);
    }

    /**
     * 得到参数名称和值 放到spel上下文
     *
     * @param pjp
     */
    private void extractArgments(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String[] names = methodSignature.getParameterNames();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < names.length; i++) {
            this.context.setVariable(names[i], args[i]);
        }
    }

    /**
     * 计算表达式
     *
     * @param expr
     * @return
     */
    public Object cacl(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            return null;
        }
        return this.parser.parseRaw(expr).getValue(this.context);
    }
}
