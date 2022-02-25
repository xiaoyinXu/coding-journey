package com.xxywebsite.spring.basics.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
@Component
public class ConstantUtil {

    public static int count;

    @Value("${spring.count:999}")
    public void setCount(int count) {
        ConstantUtil.count = count;
    }
}
