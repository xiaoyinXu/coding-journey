package com.xxywebsite.bytecode.asm;


import com.xxywebsite.bytecode.signature.MyClassLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.Serializable;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_8;

/**
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class AddCloneableInterfaceTest {

    public static byte[] addCloneableInterface(Class<?> clazz) {
        try {
            String name = clazz.getName();
            ClassReader classReader = new ClassReader(name);
            ClassWriter classWriter = new ClassWriter(classReader, 0);
            classReader.accept(new AddInterfaceAdaptor(classWriter), 0);
            return classWriter.toByteArray();
        } catch (Exception exception) {
            exception.printStackTrace();
            return new byte[0];
        }
    }

    public static class AddInterfaceAdaptor extends ClassVisitor {
        public AddInterfaceAdaptor(ClassWriter classWriter) {
            super(ASM4, classWriter); // TODO
//            this.cv = classWriter;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            String[] holding = new String[interfaces.length + 1];
            holding[holding.length - 1] = "java/lang/Cloneable";
            System.arraycopy(interfaces, 0, holding, 0, interfaces.length);
            cv.visit(V1_8, access, name, signature, superName, holding);

        }
    }



    public static void main(String[] args) throws Exception {
        byte[] bytes = AddCloneableInterfaceTest.addCloneableInterface(ABC.class);
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.defineClazz(bytes);
//
        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("clone");
        method.setAccessible(true);
        System.out.println(method.invoke(object));

    }

    public interface TestInterface {

    }

    public static class ABC implements Serializable {

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return "123";
        }
    }

}
