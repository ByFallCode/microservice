package com.byfallcode.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {
    @Value("${global.param.p1}")
    private String x;
    @Value("${global.param.p2}")
    private String y;
    @Autowired private CustomerConfigParams configParams;

    @GetMapping("/testConfig")
    public Map<String, String> configTest() {
        return Map.of("P1", x, "P2", y);
    }

    @GetMapping("/testConfigParam")
    public CustomerConfigParams configParam() {
        return configParams;
    }
}
