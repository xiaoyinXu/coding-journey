package com.xxywebsite.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class OverrideMethodTest {
    public static class BaseClass {
        public String sayHello(String name) {
            return String.format("Hello From %s", name);
        }
    }

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(BaseClass.class.getName());
        CtClass derivedCtClass = pool.makeClass("DerivedClass");
        derivedCtClass.setSuperclass(ctClass);
        CtMethod ctMethod = CtMethod.make("public String sayHello(String name){System.out.println(\"start\");return super.sayHello($1);}", derivedCtClass);
        derivedCtClass.addMethod(ctMethod);
        Class<?> clazz = derivedCtClass.toClass();
        BaseClass baseClass = ((BaseClass) clazz.newInstance());
        System.out.println(baseClass.sayHello("Cookie"));
    }
}
