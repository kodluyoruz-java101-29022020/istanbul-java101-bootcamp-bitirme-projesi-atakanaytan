package com.thesis.project.aspect;

import com.thesis.project.annotation.MethodRunningTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MethodRunningTimeAspect {

    @Around("@annotation(methodRunningTime)")
    public Object execute(ProceedingJoinPoint point, MethodRunningTime methodRunningTime) throws Throwable {

        if (!methodRunningTime.active()) {
                return point.proceed();
        }

        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object result = point.proceed();

        stopWatch.stop();

        System.out.println(className + "#" + methodName + " runned in " + stopWatch.getTotalTimeMillis()+ "ms");

        return result;
    }

}
