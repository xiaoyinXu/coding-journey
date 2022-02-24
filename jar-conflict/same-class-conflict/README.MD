A jar包和B jar包含有一个全限定名相同的类
最终类加载器只会加载一个类： CLASSPATH里面靠前jar包的类。
修改ClassPath有多种方案，可以在java -cp里指定；可以修改MANIFEST.MF里的Class-Path; 当然
最方便的做法就是修改pom文件，maven会把先声明的jar包放在Class-Path前面(或extract到far-jar里)。