package com.system.filter;

import com.alibaba.nacos.common.utils.StringUtils;
import com.auth0.jwt.interfaces.Claim;
import com.system.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
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
@RequiredArgsConstructor
public class AuthGatewayFilterFactory implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token=exchange.getRequest().getQueryParams().getFirst("token");
        String token = Objects.requireNonNull(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)).replace("Bearer ", "");;

        //Access Deny for no token
        if(StringUtils.isEmpty(token)){
            //响应HTTP状态码（401：没有访问权限）
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //请求结束
            return exchange.getResponse().setComplete();
        }

        //Verify Token
        JWTUtil.verify(token);

        //Get Role
        Map<String, Claim> claims = JWTUtil.getPayloadFromToken(token);
        String role = claims.get("role").asString();
        String path = exchange.getRequest().getPath().toString();
        PathMatcher pathMatcher = new AntPathMatcher();
        switch (role) {
            case "Administrator" -> {
                if (pathMatcher.match("/administrator/**", path)||
                    pathMatcher.match("/gridDetector/**", path)||
                    pathMatcher.match("/user/**", path))
                    return chain.filter(exchange);
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "Supervisor" -> {
                if (pathMatcher.match("/user/**", path)||
                    pathMatcher.match("/supervisor/modifySupervisor", path)||
                    pathMatcher.match("/supervisor/deleteSupervisor", path)||
                    pathMatcher.match("/report/report", path)||
                    pathMatcher.match("/report/queryReportList", path))
                    return chain.filter(exchange);
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "GridDetector" -> {
                if (pathMatcher.match("/user/**", path)||
                    pathMatcher.match("/gridDetector/modifyGridDetector", path)||
                    pathMatcher.match("/gridDetector/deleteGridDetector", path)||
                    pathMatcher.match("task/queryTaskList", path)||
                    pathMatcher.match("submission/**", path))
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
