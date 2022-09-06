package com.xxywebsite.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/9/5
 **/
public class AddClassTest {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("aa.bb.cc.Test");
        CtMethod ctMethod = CtMethod.make("public String hello() {return \"hello\";}", ctClass);
        ctClass.addMethod(ctMethod);
        Class<?> clazz = ctClass.toClass();
        Method method = clazz.getDeclaredMethod("hello");
        System.out.println(method.invoke(clazz.newInstance()));
    }
}
