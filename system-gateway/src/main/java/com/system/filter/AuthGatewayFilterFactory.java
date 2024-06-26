package com.system.filter;

import com.alibaba.nacos.common.utils.StringUtils;
import com.auth0.jwt.interfaces.Claim;
import com.system.service.IPService;
import com.system.util.IPUtil;
import com.system.util.JWTUtil;
import com.system.util.SHA256Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@CrossOrigin
public class AuthGatewayFilterFactory implements GlobalFilter  {
    private final IPService ipService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        PathMatcher pathMatcher = new AntPathMatcher();

        if(pathMatcher.match("/user/login", path) || pathMatcher.match("/user/register", path))
            return chain.filter(exchange);
        if(pathMatcher.match("/api/**", path)){
            //simple mode: allow all the ip in the database to access the api
            String remote_ip = IPUtil.getIpAddress(exchange.getRequest());
            if(ipService.getById(remote_ip) == null){
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
            //strict mode: allow the localhost to access the api
//            String local_ip = null;
//            try {
//                local_ip = InetAddress.getLocalHost().getHostAddress();
//            } catch (UnknownHostException e) {
//                throw new RuntimeException(e);
//            }
//            if(remote_ip.equals(local_ip))
//                return chain.filter(exchange);
        }

        //allow the marked ip to access the digitalScreen
        if(passVerify("digitalScreen", pathMatcher, path)){
            String remote_ip = IPUtil.getIpAddress(exchange.getRequest());
            if(ipService.getById(remote_ip) != null) {
                System.out.println(path);
                return chain.filter(pass(path, exchange));
            }
        }

        String token = Objects.requireNonNull(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .replace("Bearer ", "");;
        //Verify Token
        JWTUtil.verify(token);
        Map<String, Claim> claims = JWTUtil.getPayloadFromToken(token);
        String role = claims.get("role").asString();
        if(StringUtils.isEmpty(token)){
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        switch (role) {
            case "Administrator" -> {
                if (passVerify("administrator", pathMatcher, path))
                    return chain.filter(pass(path, exchange));
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "Supervisor" -> {
                if (passVerify("supervisor", pathMatcher, path))
                    return chain.filter(pass(path, exchange));
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            case "GridDetector" -> {
                if (passVerify("gridDetector", pathMatcher, path))
                    return chain.filter(pass(path, exchange));
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
//            case "DecisionMaker" -> {
//                if (passVerify("decisionMaker", pathMatcher, path)||
//                    pathMatcher.match("/decisionMaker/**", path)||
//                    pathMatcher.match("/digitalScreen/**", path))
//                    return chain.filter(pass(path, exchange));
//                else {
//                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                    return exchange.getResponse().setComplete();
//                }
//            }
            default -> {
                // 其他角色不授权
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }
    }

    private boolean passVerify(String character, PathMatcher pathMatcher, String path){
         return pathMatcher.match("/airData/"+character+"/**", path)||
                pathMatcher.match("/report/"+character+"/**", path)||
                pathMatcher.match("/task/"+character+"/**", path)||
                pathMatcher.match("/submission/"+character+"/**", path)||
                pathMatcher.match("/city/**", path)||
                pathMatcher.match("/user/**", path);
    }

    private ServerWebExchange pass(String path, ServerWebExchange exchange){
        String encrypt = encrypt(path);
        ServerHttpRequest host = exchange.getRequest().mutate().header("token", encrypt).build();
        return exchange.mutate().request(host).build();
    }

    private String encrypt(String input){
        String encryptedKeys = "6be629bc3fc86d8d";
        return SHA256Util.encrypt(input+encryptedKeys);
    }
}
