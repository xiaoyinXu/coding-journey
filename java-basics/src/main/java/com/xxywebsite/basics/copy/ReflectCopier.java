package com.xxywebsite.basics.copy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class ReflectCopier implements Copier {

    @Override
    public <T> T copy(Object source, Class<T> targetClazz) {

        // method-copy
//        Class<?> sourceClazz = source.getClass();
//
//        try {
//            T t = targetClazz.newInstance();
//            for (Field declaredField : targetClazz.getDeclaredFields()) {
//                String fieldName = declaredField.getName();
//                Method getter = sourceClazz.getDeclaredMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1, fieldName.length()));
//                Method setter = targetClazz.getDeclaredMethod("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1, fieldName.length()), declaredField.getType());
//                Object fieldValue = getter.invoke(source);
//                setter.invoke(t, fieldValue);
//            }
//            return t;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }


        // field-copy
        try {
            Class<?> sourceClazz = source.getClass();
            T t = targetClazz.newInstance();
            for (Field targetField : targetClazz.getDeclaredFields()) {
                String fieldName = targetField.getName();
                Field sourceField = sourceClazz.getDeclaredField(fieldName);
                sourceField.setAccessible(true);
                targetField.setAccessible(true);

                Object sourceFieldValue = sourceField.get(source);
                targetField.set(t, sourceFieldValue);
            }

            return t;

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
