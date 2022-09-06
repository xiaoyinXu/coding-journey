package com.xxywebsite.bytecode.signature;

/**
 * @author  xuxiaoyin
 * @since  2022/9/6
**/
public class MyClassLoader extends ClassLoader {
    public Class<?> defineClazz(byte[] bytes) {
        return defineClass(bytes, 0, bytes.length);
    }
}
