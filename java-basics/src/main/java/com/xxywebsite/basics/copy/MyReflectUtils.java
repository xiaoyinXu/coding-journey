package com.xxywebsite.basics.copy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class MyReflectUtils {
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                fieldList.add(declaredField);
            }
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    public static List<Method> getAllMethods(Class<?> clazz) {
        List<Method> methodList = new ArrayList<>();
        while (clazz != null) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                methodList.add(declaredMethod);
            }
            clazz = clazz.getSuperclass();
        }
        return methodList;
    }
}
