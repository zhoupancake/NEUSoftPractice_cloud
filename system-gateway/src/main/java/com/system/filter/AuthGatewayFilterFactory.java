package com.system.filter;

import com.alibaba.nacos.common.utils.StringUtils;
import com.auth0.jwt.interfaces.Claim;
import com.system.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class AuthGatewayFilterFactory implements GlobalFilter  {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token=exchange.getRequest().getQueryParams().getFirst("token");
        String token = Objects.requireNonNull(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)).replace("Bearer ", "");;

        //Access Deny for no token
        if(StringUtils.isEmpty(token)){
            //响应HTTP状态码（401：没有访问权限）
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            //请求结束
            return exchange.getResponse().setComplete();
        }

        //Verify Token
        JWTUtil.verify(token);
        System.out.println(token);
        //Get Role
        Map<String, Claim> claims = JWTUtil.getPayloadFromToken(token);
        String role = claims.get("role").asString();
        String path = exchange.getRequest().getPath().toString();
        PathMatcher pathMatcher = new AntPathMatcher();
        switch (role) {
            case "Administrator" -> {
                if (pathMatcher.match("/airData/administrator/**", path)||
                    pathMatcher.match("/report/administrator/**", path)||
                    pathMatcher.match("/task/administrator/**", path) ||
                    pathMatcher.match("/submission/administrator/**", path) ||
                    pathMatcher.match("/city/**", path) ||
                    pathMatcher.match("/user/**", path))
                    return chain.filter(exchange);
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "Supervisor" -> {
                if (pathMatcher.match("/airData/supervisor/**", path)||
                    pathMatcher.match("/report/supervisor/**", path)||
                    pathMatcher.match("/task/supervisor/**", path) ||
                    pathMatcher.match("/submission/supervisor/**", path) ||
                    pathMatcher.match("/city/**", path) ||
                    pathMatcher.match("/user/**", path))
                    return chain.filter(exchange);
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "GridDetector" -> {
                if (pathMatcher.match("/airData/gridDetector/**", path)||
                    pathMatcher.match("/report/gridDetector/**", path)||
                    pathMatcher.match("/task/gridDetector/**", path) ||
                    pathMatcher.match("/submission/gridDetector/**", path) ||
                    pathMatcher.match("/city/**", path) ||
                    pathMatcher.match("/user/**", path))
                    return chain.filter(exchange);
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "Super" -> {
                return chain.filter(exchange);
            }
            default -> {
                // 其他角色不授权
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }
    }
}
