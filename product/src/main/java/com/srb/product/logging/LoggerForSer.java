package com.srb.product.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggerForSer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerForSer.class);

    /*
     * Pointcut for all service layer methods
     */
    @Pointcut("execution(* com.srb.product.service.*.*(..))")
    public void serviceMethods() {}



    /*
     * Log method start
     */
    @Before("serviceMethods()")
    public void logMethodStart(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        LOGGER.info("Method Started: {}", methodName);
        LOGGER.info("Arguments: {}", Arrays.toString(args));
        LOGGER.info("Time: {}", LocalDateTime.now());
    }



    /*
     * Log returned value
     */
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();

        LOGGER.info("Method Completed: {}", methodName);
        LOGGER.info("Return Value: {}", result);
    }



    /*
     * Log exception if method fails
     */
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Exception exception) {

        String methodName = joinPoint.getSignature().getName();

        LOGGER.error("Exception in Method: {}", methodName);
        LOGGER.error("Exception Message: {}", exception.getMessage());
    }



    /*
     * Measure execution time
     */
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        LOGGER.info("Execution Time -> Method: {} | Time: {} ms",
                joinPoint.getSignature().getName(),
                (end - start));

        return result;
    }

}