package com.xxywebsite.bytecode.signature;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class SignatureUtil {

    public static String getMethodSignature(Method method) {
        try {

            String sig;
            try {
                Field gSig = Method.class.getDeclaredField("signature");
                gSig.setAccessible(true);
                sig = (String) gSig.get(method);
                if(sig!=null) return sig;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }

            StringBuilder sb = new StringBuilder("(");
            for(Class<?> c : method.getParameterTypes())
                sb.append((sig= Array.newInstance(c, 0).toString())
                        .substring(1, sig.indexOf('@')));
            return sb.append(')')
                    .append(
                            method.getReturnType()==void.class?"V":
                                    (sig=Array.newInstance(method.getReturnType(), 0).toString()).substring(1, sig.indexOf('@'))
                    )
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public static String getFieldSignature(Field field) {
        try {
            Field f = Field.class.getDeclaredField("signature");
            f.setAccessible(true);
            return ((String) f.get(field));
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
    private <T> Integer fn1() {
        return 1;
    }

    private int fn2() {
        return 1;
    }

    private String name;

    private Integer age;

    private long id;

    public static void main(String[] args) throws Exception {
        Method method1 = SignatureUtil.class.getDeclaredMethod("fn1");
        Method method2 = SignatureUtil.class.getDeclaredMethod("fn2");
        Field field1 = SignatureUtil.class.getDeclaredField("name");
        Field field2 = SignatureUtil.class.getDeclaredField("age");
        Field field3 = SignatureUtil.class.getDeclaredField("id");

        System.out.println(SignatureUtil.getMethodSignature(method1));
        System.out.println(SignatureUtil.getMethodSignature(method2));

        System.out.println(SignatureUtil.getFieldSignature(field1));
        System.out.println(SignatureUtil.getFieldSignature(field2));
        System.out.println(SignatureUtil.getFieldSignature(field3));
    }
}
