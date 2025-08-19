package com.xuxiaolei.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/19 10:11
 **/
@Component
public class StripApiPrefixGlobalFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        //取消/api前缀
        if (path.startsWith("/api")) {
            String newPath = path.substring(4);// 去掉 "/api"
            ServerHttpRequest newRequest = exchange.getRequest().mutate().path(newPath).build();
            exchange = exchange.mutate().request(newRequest).build();
        }

        // 继续传递请求
        return chain.filter(exchange);
    }
    // 设置优先级，数字越小越先执行
    @Override
    public int getOrder() {
        return -1;
    }
}
