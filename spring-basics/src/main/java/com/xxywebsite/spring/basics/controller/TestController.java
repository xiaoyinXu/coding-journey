package com.xxywebsite.spring.basics.controller;

import com.xxywebsite.spring.basics.classloader.ChildFirstClassLoader;
import com.xxywebsite.spring.basics.util.ConstantUtil;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

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

    @RequestMapping("/random/**")
    public String random(HttpServletRequest httpServletRequest) {
        String jsonString =
                "{\n" +
                        "   \"interfaces\": {\n" +
                        "      \"name\": \"测试接口1\",\n" +
                        "      \"mapping\": \"/test/interface1\",\n" +
                        "      \"params\": [\n" +
                        "         {\n" +
                        "            \"name\": \"orgCode\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"name\": \"theaterCode\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"date\": \"date\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         }\n" +
                        "      ],\n" +
                        "      \"response\": [\n" +
                        "         {\n" +
                        "            \"name\": \"orgCode\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"name\": \"orgName\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"name\": \"theaterCode\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"name\": \"theaterName\",\n" +
                        "            \"type\": \"string\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"name\": \"sites\",\n" +
                        "            \"type\": \"array\",\n" +
                        "            \"elements\": [\n" +
                        "               {\n" +
                        "                  \"type\": \"object\",\n" +
                        "                  \"properties\": [\n" +
                        "                     {\n" +
                        "                        \"name\": \"siteCode\",\n" +
                        "                        \"type\": \"string\"\n" +
                        "                     },\n" +
                        "                     {\n" +
                        "                        \"name\": \"siteName\",\n" +
                        "                        \"type\": \"string\"\n" +
                        "                     },\n" +
                        "                     {\n" +
                        "                        \"name\": \"count\",\n" +
                        "                        \"type\": \"number\"\n" +
                        "                     }\n" +
                        "                  ]\n" +
                        "               }\n" +
                        "            ]\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"name\": \"meta\",\n" +
                        "            \"type\": \"object\",\n" +
                        "            \"properties\": [\n" +
                        "               {\n" +
                        "                  \"name\": \"field1\",\n" +
                        "                  \"type\": \"string\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                  \"name\": \"field2\",\n" +
                        "                  \"type\": \"string\"\n" +
                        "               }\n" +
                        "            ]\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}";


        return httpServletRequest.getRequestURL().toString();
    }

    @RequestMapping("/submit")
    public String submit(String jarPath, String mainClass, String... arguments) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        URL url = Paths.get(jarPath).toUri().toURL();
        ChildFirstClassLoader childFirstClassLoader = new ChildFirstClassLoader(new URL[]{url});
        Class<?> clazz = childFirstClassLoader.loadClass(mainClass);
        Method method = clazz.getDeclaredMethod("main", String[].class);
        new Thread(() -> {
            try {
                method.invoke(null, new Object[]{arguments});
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }).start();
        ;
        return "success";
    }

}
