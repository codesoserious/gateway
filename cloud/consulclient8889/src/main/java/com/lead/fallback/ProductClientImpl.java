package com.lead.fallback;

import com.lead.clients.ProductClient;
import com.lead.entity.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Administrator
 */
@Component
public class ProductClientImpl implements ProductClient {
    @Override
    public HashMap<String, Object> showMsg() {
        return null;
    }

    @Override
    public HashMap<String, Object> showMsg(Product product) {
        HashMap<String, Object> map = new HashMap<>(10);
        map.put("msg", "服务降级");
        return map;
    }
}
