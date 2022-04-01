package com.xxywebsite.basics.copy;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public interface Copier {
    <T> T copy(Object source, Class<T> targetClazz);
}
