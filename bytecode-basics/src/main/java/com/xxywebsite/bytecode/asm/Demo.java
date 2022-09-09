package com.xxywebsite.bytecode.asm;

import com.xxywebsite.bytecode.signature.MyClassLoader;
import lombok.NonNull;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * FROM https://www.baeldung.com/java-asm
 *
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class Demo {
    @NonNull
    public static class A<T> implements Serializable, Comparable<A> {
        private int a;
        private long b;
        private boolean c;
        private T t;

        @Override
        public int compareTo(A o) {
            return 0;
        }
    }


    public static void main(String[] args) throws IOException {
        CustomClassWriter customClassWriter = new CustomClassWriter();
        customClassWriter.addField();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.defineClazz(customClassWriter.writer.toByteArray());
        System.out.println(clazz);
    }

    public static class CustomClassWriter {
        AddFieldAdapter addFieldAdapter;

        public byte[] addField() {
            addFieldAdapter = new AddFieldAdapter(
                    "aNewBooleanField",
                    "Z",
                    org.objectweb.asm.Opcodes.ACC_PUBLIC,
                    writer);
            reader.accept(addFieldAdapter, 0);
            return writer.toByteArray();
        }


        //        static String className = "java.lang.Integer";
        static String className = "com.xxywebsite.bytecode.asm.Demo$A";
        static String cloneableInterface = "java/lang/Cloneable";
        ClassReader reader;
        ClassWriter writer;

        public CustomClassWriter() throws IOException {
            reader = new ClassReader(className);
            writer = new ClassWriter(reader, 0);
        }
    }

    public static class AddFieldAdapter extends ClassVisitor {
        private String fieldName;
        private String fieldType;
        private String fieldDefault;
        private int access = org.objectweb.asm.Opcodes.ACC_PUBLIC;
        private boolean isFieldPresent;

        public AddFieldAdapter(
                String fieldName, String fieldType, int fieldAccess, ClassVisitor cv) {
            super(ASM4, cv);
            this.cv = cv;
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.access = fieldAccess;
        }

        @Override
        public FieldVisitor visitField(
                int access, String name, String desc, String signature, Object value) {
            if (name.equals(fieldName)) {
                isFieldPresent = true;
            }
            return cv.visitField(access, name, desc, signature, value);
        }


        @Override
        public void visitEnd() {
            if (!isFieldPresent) {
                FieldVisitor fv = cv.visitField(
                        access, fieldName, fieldType, null, null);
                if (fv != null) {
                    fv.visitEnd();
                }
            }
            cv.visitEnd();
        }
    }


}
