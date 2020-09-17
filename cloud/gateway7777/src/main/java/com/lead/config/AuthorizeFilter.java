package com.lead.config;

import com.lead.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @title: AuthorizeFilter
 * @projectName: xm
 * @description: TODO
 * @author: ZP
 * @date: 2020/9/17  15:15
 */
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {


    /**
     * 登录获取配置文件中用户登录的url
     */
    @Value("${login.url}")
    private String loginUrl;

    public AuthorizeFilter() {
    }

//    private ObjectMapper objectMapper;
//    public AuthorizeFilter(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;}
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        log.info("获取的请求路劲{}", request.getURI().getPath());
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        //3. 如果是登录请求则放行
        if (request.getURI().getPath().contains(loginUrl)) {
            return chain.filter(exchange);
        }
        //TODO  获取请求头 ,并用请求头中获取令牌
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");
        log.info("令牌为:{}", token);
        //TODO  判断请求头中是否有令牌
        if (StringUtils.isEmpty(token)) {
            throw  new RuntimeException("请先登录");
        }
        //9. 如果请求头中有令牌则解析令牌
        try {
            JwtUtil.parseJWT(token);
        } catch (ExpiredJwtException e) {
            String message="Allowed clock skew";
            //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
            if (e.getMessage().contains(message)) {
                throw new RuntimeException("令牌过期异常");
            } else {
                throw  new RuntimeException("认证失败");
            }
        } catch (Exception e) {
           throw  new RuntimeException("认证失败");
        }

        return chain.filter(exchange);
    }


//    /**
//     * 认证错误输出
//     *
//     * @param resp 响应对象
//     * @param mess 错误信息
//     * @return
//     */
//    private Mono<Void> authErro(ServerHttpResponse resp, String mess) {
//        //7. 响应中放入返回的状态吗, 没有权限访问
//        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
//        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//        ReturnData<String> returnData = new ReturnData<>(org.apache.http.HttpStatus.SC_UNAUTHORIZED, mess, mess);
//        String returnStr = "";
//        try {
//            returnStr = objectMapper.writeValueAsString(returnData);
//        } catch (JsonProcessingException e) {
//            log.error(e.getMessage(), e);
//        }
//        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
//        return resp.writeWith(Flux.just(buffer));
//    }

    @Override
    public int getOrder() {
        return 0;
    }
}
