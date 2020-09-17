package com.lead.controller;

import com.lead.clients.ProductClient;
import com.lead.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@Slf4j
public class UserController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ProductClient productClient;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/products/{id}")
    public String showsProduct(@PathVariable Integer id) {
        HashMap<String, String> map = new HashMap<>();
        log.info("得到的id{}", id);
        String forObject = restTemplate.getForObject("http://ip:port/**/*", String.class);
        return forObject;
    }

    @GetMapping("/products/{name}")
    public HashMap<String, String> showName(@PathVariable String name) {
        HashMap<String, String> map = new HashMap<>();
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-test");
        for (ServiceInstance instance : instances) {
            log.info("IP地址{}", instance.getHost());
            log.info("端口号{}", instance.getPort());
            map.put("端口号{}" + instance.getPort(), "IP地址{}" + instance.getHost());
        }
        return map;
    }

    @GetMapping("/orders/{id}")
    public ServiceInstance getId(@PathVariable Integer id) {
        log.info("获得前台的id{}", id);
        //consul-test是服务名
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-test");
        return serviceInstance;
    }

    /**
     *
     */
    @GetMapping("/user/products/{id}")
//    @HystrixCommand(fallbackMethod = "userToMsgs")
    public HashMap<String, Object> userToMsg(@PathVariable Integer id) {
        if (id > 1) {
            throw new RuntimeException("服务降级");
        }
//        productClient.showMsg()
        HashMap<String, Object> map = productClient.showMsg(new Product().setId(id).setMessage("水果是美味的").setName("美味"));
        return map;
    }

//    public HashMap<String, Object> userToMsgs(@PathVariable Integer id) {
////        productClient.showMsg()
//        HashMap<String, Object> map = new HashMap<>(10);
//        map.put("msg", "服务降级");
//        return map;
//    }

}
