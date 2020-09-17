package com.lead.clients;

import com.lead.entity.Product;
import com.lead.fallback.ProductClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

/**
 * 调用商品服务的feign组件
 *
 * @author Administrator
 */

@FeignClient(value = "consul-test", fallback = ProductClientImpl.class)
public interface ProductClient {

    /**
     * 展示信息
     *
     * @return
     */
    @GetMapping("/products/msg")
    HashMap<String, Object> showMsg();

    /**
     * 展示信息
     *
     * @param product
     * @return
     */
    @PostMapping("/products/one")
    HashMap<String, Object> showMsg(@RequestBody Product product);
}
