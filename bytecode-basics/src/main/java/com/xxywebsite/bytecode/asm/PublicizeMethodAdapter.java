package com.xxywebsite.bytecode.asm;

import com.xxywebsite.bytecode.signature.MyClassLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class PublicizeMethodAdapter extends ClassVisitor {
    private int a;
    private long b;

    public void fn() {

    }

    public static void inspectMethod(String clazzName) throws IOException {
        ClassReader classReader = new ClassReader("com.xxywebsite.bytecode.asm.PublicizeMethodAdapter");
        ClassWriter classWriter = new ClassWriter(classReader, 0);
        PublicizeMethodAdapter publicizeMethodAdapter = new PublicizeMethodAdapter(classWriter);
        classReader.accept(publicizeMethodAdapter, 0);
        byte[] bytes = classWriter.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.defineClazz(bytes);
        System.out.println(clazz == PublicizeMethodAdapter.class);
    }

    public static void main(String[] args) throws Exception {
        inspectMethod("com.xxywebsite.bytecode.asm.PublicizeMethodAdapter");
    }

    PrintWriter pw = new PrintWriter(System.out);
    private TraceClassVisitor tracer;

    public PublicizeMethodAdapter(ClassVisitor cv) {
        super(ASM4, cv);
        this.cv = cv;
        tracer = new TraceClassVisitor(cv,pw);
    }

    @Override
    public MethodVisitor visitMethod(
            int access,
            String name,
            String desc,
            String signature,
            String[] exceptions) {
        if (name.equals("fn")) {
            System.out.println("Visiting unsigned method");
            return tracer.visitMethod(
                    ACC_PUBLIC + ACC_STATIC, name, desc, signature, exceptions);
        }
        return tracer.visitMethod(
                access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd(){
        tracer.visitEnd();
//        System.out.println(tracer.p.getText());
    }
}
