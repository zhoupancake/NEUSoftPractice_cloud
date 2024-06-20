package com.system.interceptor;

import com.system.util.VerifyUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String path = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        if (path.equals("/user/login") || pathMatcher.match("/api/**", path))
            return true;
        String authToken = request.getHeader("token");
        if (VerifyUtil.verify(authToken, path)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access deny");
            return false;
        }
        return true;
    }
}
