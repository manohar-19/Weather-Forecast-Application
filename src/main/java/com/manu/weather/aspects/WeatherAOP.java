package com.manu.weather.aspects;

import com.manu.weather.model.WeatherForecastFinal;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WeatherAOP {
    private static final Logger log = LoggerFactory.getLogger(WeatherAOP.class);

    @Pointcut("execution(* com.manu.weather.controller.*.*(..) )")
    public void logForController(){}

    @Pointcut("execution(* com.manu.weather.service.*.*(..) )")
    public void logForService(){}

    @Pointcut("logForController() || logForService()")
    public void logForControllerService(){}

    @Before("logForControllerService()")
    public void logForWeatherController(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info(String.valueOf(methodSignature));

        Object[] args = joinPoint.getArgs();
        for (Object val : args) {
            log.info("Arguments passed value :: " + val);
        }
    }
    
    @AfterReturning(pointcut = "execution(com.manu.weather.model.WeatherForecastFinal com.manu.weather.service.WeatherService.getFinalValues(..))",
                    returning = "result")
    public void logForFinalValues(JoinPoint theJoinPoint, WeatherForecastFinal result){
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        log.info("AfterReturning :: "+String.valueOf(methodSignature));
        log.info("result :: "+result.toString());
    }

}
