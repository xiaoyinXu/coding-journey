package com.xxywebsite.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/9/5
 **/
public class ExtendClassTest {
    public String hello() {
        return "Hello From Cookie";
    }

    public static void main(String[] args) throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("MyClass");
        ctClass.setSuperclass(classPool.get("com.xxywebsite.bytecode.javassist.ExtendClassTest"));
        CtMethod ctMethod = ctClass.getDeclaredMethod("hello");
        ctMethod.insertBefore("System.out.println(\"111\")");
        Class<?> clazz = ctClass.toClass();
        Method method = clazz.getDeclaredMethod("hello");
        System.out.println(method.invoke(clazz.newInstance()));
    }
}
