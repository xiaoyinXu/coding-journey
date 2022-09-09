package com.xxywebsite.bytecode.asm;

import org.objectweb.asm.*;

import java.io.*;

public class ClassModifierDemo {

    public int add(int a, int b) {
        return  a + b;
    }

    public static class ModifierMethodWriter extends MethodVisitor {

        private String methodName;

        private MethodVisitor methodVisitor;

        public ModifierMethodWriter(int api, MethodVisitor mv, String methodName) {
            super(api, null);
            this.methodName = methodName;
            this.methodVisitor = mv;
        }

        //This is the point we insert the code. Note that the instructions are added right after
        //the visitCode method of the super class. This ordering is very important.
        @Override
        public void visitCode() {
//            super.visitCode();
//            super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            super.visitLdcInsn("method is: " + methodName);
//            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("method is: " + methodName);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }


    }

    //Our class modifier class visitor. It delegate all calls to the super class
    //Only makes sure that it returns our MethodVisitor for every method
    public static class ModifierClassWriter extends ClassVisitor {
        private int api;

        public ModifierClassWriter(int api, ClassWriter cv) {
            super(api, cv);
            this.api = api;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                                         String signature, String[] exceptions) {

            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            ModifierMethodWriter mvw = new ModifierMethodWriter(api, mv, name);
            return mvw;
        }


    }

    public static void main(String[] args) throws IOException {
        InputStream in = ClassModifierDemo.class.getResourceAsStream("/com/xxywebsite/bytecode/asm/ClassModifierDemo.class");
        ClassReader classReader = new ClassReader(in);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //Wrap the ClassWriter with our custom ClassVisitor
        ModifierClassWriter mcw = new ModifierClassWriter(Opcodes.ASM4, cw);
        classReader.accept(mcw, 0);

        //Write the output to a class file
        File outputDir = new File("out/com/geekyarticles/asm");
        outputDir.mkdirs();
        DataOutputStream dout = new DataOutputStream(new FileOutputStream(new File(outputDir, "ClassModificationDemo.class")));
        dout.write(cw.toByteArray());
    }

}