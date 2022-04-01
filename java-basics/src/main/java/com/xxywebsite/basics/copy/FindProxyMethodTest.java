package com.xxywebsite.basics.copy;

import com.google.common.reflect.ClassPath;
import lombok.Data;
import net.sf.cglib.beans.BeanCopier;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class FindProxyMethodTest {
    @Data
    public static class A {
        private String myField;
    }
    @Data
    public static class B {
        private String myField;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        List<String> classes = new ArrayList<>();
        String targetMethodName = "getMyField";
        BeanCopier beanCopier = BeanCopier.create(A.class, B.class, false);

        List<String> loadedClasses = getLoadedClasses(ClassLoader.getSystemClassLoader())
                .stream()
                .filter(name -> name.startsWith("net"))
                .collect(Collectors.toList());
        // 从classpath下轮询所有类，看哪个类含有getMyField方法
        for (String name : loadedClasses) {
            Class<?> clazz = Class.forName(name);
            List<Method> allMethods = MyReflectUtils.getAllMethods(clazz);

            boolean match = allMethods.stream()
                    .map(Method::getName)
                    .anyMatch(methodName -> targetMethodName.equals(methodName));
            if (match) {
                classes.add(name);
            }
        }

        System.out.println(classes);
    }



    public static List<String> getLoadedClasses(ClassLoader byClassLoader) {
        List<String> classnameList = new ArrayList<>();
        Class clKlass = byClassLoader.getClass();
        System.out.println("Classloader: " + clKlass.getCanonicalName());
        while (clKlass != java.lang.ClassLoader.class) {
            clKlass = clKlass.getSuperclass();
        }
        try {
            java.lang.reflect.Field fldClasses = clKlass
                    .getDeclaredField("classes");
            fldClasses.setAccessible(true);
            Vector classes = (Vector) fldClasses.get(byClassLoader);
            for (Iterator iter = classes.iterator(); iter.hasNext();) {
                Class clazz = (Class) iter.next();
                classnameList.add(clazz.getName());
            }
            return classnameList;
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
