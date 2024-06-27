package com.system.interceptor;

import com.system.util.VerifyUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * The micro-service interceptor to check whether the gateway allow the incoming request
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String authToken = request.getHeader("token");
        String path = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        if (pathMatcher.match("/api/**", path))
            return true;
        if (!VerifyUtil.verify(authToken, path)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access deny");
            return false;
        }
        return true;
    }
}
