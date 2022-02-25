package com.xxywebsite.spring.basics.controller;

import com.xxywebsite.spring.basics.util.ConstantUtil;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
@RestController
@RequestMapping("/test")
@DependsOn("constantUtil")
public class TestController {
    /**
     * 对于一些spring bean的静态属性/方法，如果在spring容器启动时有内部调用，可能会出现顺序问题。
     */
    @PostConstruct
    public void init() {
        System.out.println(ConstantUtil.count);
    }
    @RequestMapping("/hello")
    public String hello() {
        return "Hello From Cookie";
    }
}
