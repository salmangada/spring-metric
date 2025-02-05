package com.sal.app_metrics.aop;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryMetricsAspect {

    private final MeterRegistry meterRegistry;


    public RepositoryMetricsAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Around("execution(* com.sal.app_metrics.repository..*(..))")
    public Object recordExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        Timer.Sample sample = Timer.start();

        try {
            return joinPoint.proceed();
        } finally {
            sample.stop(Timer.builder("db.query.execution.time")
                    .description("Time taken to execute repository method")
                    .tags("method", methodName)
                    .register(meterRegistry));
        }
    }
}