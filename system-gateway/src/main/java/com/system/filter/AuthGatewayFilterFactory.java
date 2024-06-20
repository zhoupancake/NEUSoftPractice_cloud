package com.system.filter;

import com.alibaba.nacos.common.utils.StringUtils;
import com.auth0.jwt.interfaces.Claim;
import com.system.util.IPUtil;
import com.system.util.JWTUtil;
import com.system.util.SHA256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class AuthGatewayFilterFactory implements GlobalFilter  {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        PathMatcher pathMatcher = new AntPathMatcher();
        if(pathMatcher.match("/user/login", path))
            return chain.filter(exchange);
        if(pathMatcher.match("/api/**", path)){
            String remote_ip = IPUtil.getIpAddress(exchange.getRequest());
            String local_ip = null;
            try {
                local_ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            if(remote_ip.equals(local_ip))
                return chain.filter(exchange);
        }
        String token = Objects.requireNonNull(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)).replace("Bearer ", "");;
        //Verify Token
        JWTUtil.verify(token);
        //Get Role
        Map<String, Claim> claims = JWTUtil.getPayloadFromToken(token);
        String role = claims.get("role").asString();


        //Access Deny for no token
        if(StringUtils.isEmpty(token)){
            //响应HTTP状态码（401：没有访问权限）
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            //请求结束
            return exchange.getResponse().setComplete();
        }
        switch (role) {
            case "Administrator" -> {
                if (pathMatcher.match("/airData/administrator/**", path)||
                    pathMatcher.match("/report/administrator/**", path)||
                    pathMatcher.match("/task/administrator/**", path) ||
                    pathMatcher.match("/submission/administrator/**", path) ||
                    pathMatcher.match("/city/**", path) ||
                    pathMatcher.match("/user/**", path)) {
                    String encrypt = encrypt(path);
                    ServerHttpRequest host = exchange.getRequest().mutate().header("token", encrypt).build();
                    ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
                }
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
                    pathMatcher.match("/user/**", path)){
                    String encrypt = encrypt(path);
                    ServerHttpRequest host = exchange.getRequest().mutate().header("token", encrypt).build();
                    ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
                }
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
                    pathMatcher.match("/user/**", path)){
                    String encrypt = encrypt(path);
                    ServerHttpRequest host = exchange.getRequest().mutate().header("token", encrypt).build();
                    ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
                }
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

    private String encrypt(String input){
        String encryptedKeys = "6be629bc3fc86d8d";
        return SHA256Util.encrypt(input+encryptedKeys);
    }
}
