package com.xxywebsite.basics.copy;

import com.google.common.reflect.ClassPath;
import com.xxywebsite.basics.anonymous.AnonymousTest;
import org.apache.tools.ant.types.Assertions;
import org.apache.tools.ant.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class OtherTest {
    public static void main(String[] args) throws Exception {
        ClassPath classPath = ClassPath.from(OtherTest.class.getClassLoader());
        Method method = OtherTest.class.getClassLoader().getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredMethod("findLoadedClass", String.class);
        method.setAccessible(true);
        Set<String> classes = classPath
                .getAllClasses()
                .stream()
                .map(ClassPath.ClassInfo::getName)
                .filter(name -> name.startsWith("com.xxywebsite"))
                .collect(Collectors.toSet());
        classes = new TreeSet<>(classes);
        System.out.println(classes.size());

        AnonymousTest anonymousTest = new AnonymousTest();
        System.out.println(AnonymousTest.class);

        classPath = ClassPath.from(OtherTest.class.getClassLoader());
        classes = classPath
                .getAllClasses()
                .stream()
                .map(ClassPath.ClassInfo::getName)
                .collect(Collectors.toSet());
        System.out.println(classes.size());
    }
}
