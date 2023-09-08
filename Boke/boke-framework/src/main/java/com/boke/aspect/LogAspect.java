package com.boke.aspect;

import com.alibaba.fastjson.JSON;
import com.boke.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DreamRay
 * @date 2023/9/6 10:39
 * @mood happy
 */
@Component
@Aspect
@Slf4j
//切面类
public class LogAspect {
    //确定切点
    //参数：对哪个方法进行增强
    @Pointcut("@annotation(com.boke.annotation.SystemLog)")
    public void pt(){

    }
    //通知方法
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //让目标方法执行
        Object ret;
        try {
            //执行方法之前
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            //执行方法之后
            handleAfter(ret);
        } finally {
            //无论有没有异常都会执行这里
            //log日志打印, System.lineSeparator()换行符
            log.info("=======End=========" + System.lineSeparator());
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        //获取当前线程的request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取方法签名，把签名转为MethodSignature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取被增强方法上的注解对象
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);

        log.info("=======Start=========" );
        //打印请求URL
        log.info("URL             : {}",request.getRequestURL());
        //打印描述信息
        log.info("BusinessName    : {}",systemLog.BusinessName());
        //打印Http method
        log.info("Http method     : {}",request.getMethod());
        //打印调用controller的全路径以及执行方法
        log.info("Class Method    : {}.{}",methodSignature.getDeclaringTypeName(),methodSignature.getName());
        //打印请求的IP
        log.info("IP              : {}",request.getRemoteHost());
        //打印请求入参
        log.info("Request Args    : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private void handleAfter(Object ret) {
        //打印出参
        log.info("Response       : {}",JSON.toJSONString(ret));
    }
}
