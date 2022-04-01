package com.xxywebsite.basics.copy;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class MyBeanCopier implements Copier {
    @Override
    public <T> T copy(Object source, Class<T> targetClazz) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClazz, false);
        T t = null;
        try {
            t = targetClazz.newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }
        beanCopier.copy(source, t, null);
        return t;
    }
}
