package com.sal.app_metrics.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class MetricInterceptor implements HandlerInterceptor {

    private final MeterRegistry registry;

    public MetricInterceptor(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    @SuppressWarnings({})
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", Instant.now());
        registry.counter("http.requests.total",
                        "method", request.getMethod(),
                        "uri", request.getRequestURI())
                .increment();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // API Response Time metric
        Instant startTime = (Instant) request.getAttribute("startTime");
        long duration = TimeUnit.MILLISECONDS
                .convert(Instant.now().minusMillis(startTime.toEpochMilli()).toEpochMilli(), TimeUnit.NANOSECONDS);

        registry.timer("http.request.duration",
                        "method", request.getMethod(),
                        "uri", request.getRequestURI(),
                        "status", String.valueOf(response.getStatus()))
                .record(duration, TimeUnit.MILLISECONDS);
    }
}