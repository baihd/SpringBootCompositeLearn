package com.composite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    //操作类型
    String action();

    //对象类型
    String itemType() default "";

    //对象id
    String itemId() default "";

    //其他参数
    String param() default "";
}
