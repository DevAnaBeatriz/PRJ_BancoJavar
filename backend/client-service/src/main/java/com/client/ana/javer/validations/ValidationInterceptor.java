package com.client.ana.javer.validations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("POST") || request.getMethod().equalsIgnoreCase("PUT")) {
            String contentType = request.getContentType();
            if (contentType == null || !contentType.contains("application/json")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Requisição inválida: Content-Type não suportado");
                return false;
            }
        }
        return true;
    }
}

