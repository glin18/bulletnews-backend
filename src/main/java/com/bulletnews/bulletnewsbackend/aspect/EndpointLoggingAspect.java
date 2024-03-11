package com.bulletnews.bulletnewsbackend.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class EndpointLoggingAspect {

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void logBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("Before method: {} | Request URL: {} | Method: {}",
                joinPoint.getSignature().getName(), request.getRequestURL(), request.getMethod());
    }

    @After("@within(org.springframework.web.bind.annotation.RestController)")
    public void logAfter(JoinPoint joinPoint) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        log.info("After method: {} | Response Status: {} ", joinPoint.getSignature().getName(),
                response != null ? response.getStatus() : "response is null");
    }

}
