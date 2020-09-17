package com.lead.controller;

import com.lead.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class ProductController {


    @GetMapping("/products/msg")
    public Map<String, Object> showMsg(String msg) {
        HashMap<String, Object> map = new HashMap<>(100);
        map.put("port", "8891");
        map.put("msg", "请求成功");
        map.put("data", msg);
        return map;
    }

    @PostMapping("/products/one")
    public Map<String, Object> showMsg(@RequestBody Product product) {
        HashMap<String, Object> map = new HashMap<>(100);
        map.put("port", "8891");
        map.put("msg", "请求成功");
        map.put("data", product);
        return map;
    }

    @PostMapping("/orders/{one}")
    public Map<String, Object> showOrder(@PathVariable String one, @RequestParam("p") Integer p) {
        HashMap<String, Object> map = new HashMap<>(100);
        map.put("port", "8890");
        map.put("msg", "请求成功");
        map.put("order", "请求成功");
        map.put("one", one);
        map.put("p", p);
        return map;
    }
}
