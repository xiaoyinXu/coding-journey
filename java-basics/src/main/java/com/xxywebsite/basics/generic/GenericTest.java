package com.xxywebsite.basics.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author xuxiaoyin
 * @since 2022/4/8
 **/
public class GenericTest {
    public static class GenericClass<T> {

    }

    public static interface GenericInterface<T> {

    }

    public static class MyClass extends GenericClass<Integer> implements GenericInterface<String> {
        private List<Number> list;

        public static List<Character> fn(List<Double> param) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Class<MyClass> clazz = MyClass.class;
        Method method = MyClass.class.getDeclaredMethod("fn", List.class);
        Field field = MyClass.class.getDeclaredField("list");

        Type type1 = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
        Type type2 = ((ParameterizedType) clazz.getGenericInterfaces()[0]).getActualTypeArguments()[0];
        Type type3 = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
        Type type4 = ((ParameterizedType) method.getGenericParameterTypes()[0]).getActualTypeArguments()[0];
        Type type5 = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

        System.out.println(type1);
        System.out.println(type2);
        System.out.println(type3);
        System.out.println(type4);
        System.out.println(type5);
    }
}
