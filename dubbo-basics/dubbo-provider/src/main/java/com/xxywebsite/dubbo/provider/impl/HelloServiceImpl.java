package com.xxywebsite.dubbo.provider.impl;

import com.xxywebsite.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Hello From Cookie";
    }
}
