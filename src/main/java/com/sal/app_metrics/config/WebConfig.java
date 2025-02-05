package com.sal.app_metrics.config;

import com.sal.app_metrics.interceptor.MetricInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MetricInterceptor metricInterceptor;

    public WebConfig(MetricInterceptor metricInterceptor) {
        this.metricInterceptor = metricInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(metricInterceptor);
    }
}