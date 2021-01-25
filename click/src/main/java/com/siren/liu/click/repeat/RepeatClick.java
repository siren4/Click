package com.siren.liu.click.repeat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LiuG on 2019-06-06.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RepeatClick {

    /**
     * 相同view再次响应的时间间隔
     */
    long delay() default 1000;

    /**
     * 不同view再次响应的时间间隔
     */
    long delayViews() default 500;
}
