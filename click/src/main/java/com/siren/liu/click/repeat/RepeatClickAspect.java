package com.siren.liu.click.repeat;

import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by LiuG on 2019-06-06.
 */
@Aspect
public class RepeatClickAspect {

    /**
     * 切面表达式，声明需要过滤的类和方法
     * 例如：execution中的内容需要替换成@RepeatClick注解的全路径。
     */
    @Pointcut("execution(@com.siren.liu.click.repeat.RepeatClick * *(..))")
    public void methodAnnotated() {
    }

    /**
     * 定义切面，包裹切点方法
     */
    @Around("methodAnnotated()")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出方法的参数
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }
        }
        if (view == null) {
            return;
        }
        //取出方法的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(RepeatClick.class)) {
            return;
        }
        RepeatClick singleClick = method.getAnnotation(RepeatClick.class);
        if (!RepeatHelper.getInstance().checkRepeat(view, singleClick.delay(), singleClick.delayViews())) {
            //执行目标方法
            joinPoint.proceed();
        }
    }

}
