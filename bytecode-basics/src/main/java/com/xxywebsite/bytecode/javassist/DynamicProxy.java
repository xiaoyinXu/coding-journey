package com.xxywebsite.bytecode.javassist;

import javassist.*;
import javassist.bytecode.MethodInfo;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;

/**
 * implements dynamic proxy using javassist
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class DynamicProxy {
//    public static <T> T getProxyInstance(Class<T> clazz, MethodInterceptor methodInterceptor) {
//        try {
//            ClassPool pool = ClassPool.getDefault();
//            CtClass ctClass = pool.makeClass("MyProxy");
//            ctClass.setSuperclass(pool.get(clazz.getName()));
//
//            // 拦截每一个方法
//            CtMethod[] methods = ctClass.getMethods();
//            for (CtMethod ctMethod : methods) {
//                MethodInfo methodInfo = ctMethod.getMethodInfo();
//                CtClass[] parameterTypes = ctMethod.getParameterTypes();
//                ctMethod.instrument(new ExprEditor(){
//                    @Override
//                    public void edit(MethodCall m) throws CannotCompileException {
//                        super.edit(m);
//                    }
//                });
//                ctMethod.instrument(new CodeConverter(){
//                    @Override
//                    public void redirectMethodCall(CtMethod origMethod, CtMethod substMethod) throws CannotCompileException {
//                        super.redirectMethodCall(origMethod, substMethod);
//                    }
//                });
//
//                clazz.getDeclaredMethod(methodInfo)
//            }
//
//            Class<?> proxyClazz = ctClass.toClass();
//
//            return (T) proxyClazz.newInstance();
//        } catch (Exception e) {
//            return null;
//        }
//
//    }
//
//    public interface MethodInterceptor {
//        Object intercept(Method method, Object instance, Object[] args);
//    }
//
//    public static class ProxyTarget {
////        private Object
////
//        Object invoke(Method method) {
//            return null;
//        }
//    }
//
//    public static class A {
//        public String sayHello() {
//            return "123";
//        }
//    }
//
//    public static void main(String[] args) {
//        A a = DynamicProxy.getProxyInstance(A.class, new MethodInterceptor() {
//            @Override
//            public Object intercept(Method method, Object instance, Object[] args) {
//                // before
//                System.out.println("start");
//                Object retVal = null;
//                try {
//                    retVal = method.invoke(instance, args);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//                // after
//                System.out.println("end");
//                return retVal;
//            }
//        });
//        System.out.println(a.sayHello());
//    }
}

