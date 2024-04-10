package com.mumu.java_tools.javaassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaAssistDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/10 11:47
 */
public class JavaAssistDemo {

    /** 字节码文件输出目录 */
    public static final String CLASSES_OUT_DIR = "F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_tools\\javaassist\\target";

    public static void main(String[] args) {
        demo1();

        // demo2();
    }

    /**
     *
     * @return void
     * @date 2024/4/10 17:02
     */
    public static void demo1() {
        try {
            // 1. 创建一个新的 ClassPool 实例
            ClassPool classPool = ClassPool.getDefault();

            // 2. 创建一个新的 CtClass 对象，表示一个新的类
            CtClass ctClass = classPool.makeClass("DynamicClass");

            // 3. 添加 import 语句
            classPool.importPackage("java.util.List");
            classPool.importPackage("java.util.ArrayList;");
            classPool.importPackage("java.util.LinkedList");

            // 4. 添加字段
            ctClass.addField(CtField.make("private int age;", ctClass));
            ctClass.addField(new CtField(CtClass.intType, "name", ctClass));

            // 5. 添加一个方法
            CtMethod method = CtNewMethod.make(
                    "public static void aa() { \n java.util.List/*<String>*/ l2 = new java.util.LinkedList/*<>*/(); \n}", ctClass);
            ctClass.addMethod(method);

            CtMethod addMethod = CtNewMethod.make(" public static void add(int a, int b) {\n" +
                    "        java.util.List/*<Integer>*/ list = new java.util.ArrayList/*<>*/(); \n" +
                    "        int res = a + b;\n" +
                    "        System.out.println(res);\n" +
                    "    }", ctClass);

            // 将新方法添加到新类中
            ctClass.addMethod(addMethod);


            // 创建内部类
            CtClass innerClass = ctClass.makeNestedClass("InnerClass", true);
            innerClass.setModifiers(Modifier.PUBLIC | Modifier.STATIC); // 设置内部类为 public static

            // 为内部类添加方法
            CtMethod innerMethod = CtNewMethod.make("@Deprecated\npublic void innerMethod() { System.out.println(\"Inner method.\"); }", innerClass
            );
            innerClass.addMethod(innerMethod);



            // 添加一个接口
            CtClass anInterface = classPool.get("java.util.List"); // 假设我们要实现List接口
            ctClass.addInterface(anInterface);

            // 继承另一个类
            CtClass superClass = classPool.get("java.util.ArrayList<Integer>"); // 假设我们想要继承ArrayList
            ctClass.setSuperclass(superClass);



            // 6. 添加构造方法
            ctClass.addConstructor(CtNewConstructor.make("public DynamicClass() {System.out.println(\"我是构造方法 \");}", ctClass));

            // 将新类写入文件系统，以便调试和验证
            ctClass.writeFile(CLASSES_OUT_DIR);
            // 保存外部类和内部类的 .class 文件
            // outerClass.writeFile(); // 默认写入当前目录，也可以指定路径
            innerClass.writeFile(CLASSES_OUT_DIR); // 确保也保存内部类的字节码

            // 加载新类并调用新方法
            Class<?> clazz = ctClass.toClass();

            Object instance = clazz.newInstance();
            clazz.getMethod("add", int.class, int.class).invoke(null, 1, 2);

            // 使用自定义的类加载器加载新类
            // ClassLoader loader = new JavaAssistExample.Loader(classPool);
            // Class<?> clazz2 = ctClass.toClass(loader, null);
            // clazz2.getMethod("dynamicMethod").invoke(null);
        } catch (CannotCompileException | ReflectiveOperationException | IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return void
     * @date 2024/4/10 17:03
     */
    public static void demo2() {
        String from = "F:\\Code\\WorkSpace\\hlby_server\\trunk\\hf-parent\\hf-client\\target\\classes\\com\\cxx\\hf\\ClientFrame.class";
        String to = CLASSES_OUT_DIR + "\\ClientFrame.class";
        try {
            // 创建一个新的 ClassPool 实例
            ClassPool classPool = ClassPool.getDefault();

            // 加载指定的 Java 类字节码文件
            FileInputStream fileInputStream = new FileInputStream(from);
            CtClass ctClass = classPool.makeClass(fileInputStream);

            // 将 CtClass 对象转换为字节码数组
            byte[] bytecode = ctClass.toBytecode();


            // 将字节码数组写入文件
            FileOutputStream fos = new FileOutputStream(to);
            fos.write(bytecode);
            fos.close();

            System.out.println("Java class file converted to bytecode successfully!");

        } catch (IOException | CannotCompileException e) {
            e.printStackTrace();
        }
    }

    static class Loader extends ClassLoader {
        private ClassPool pool;

        public Loader(ClassPool pool) {
            this.pool = pool;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                CtClass cc = pool.get(name);
                byte[] bytecode = cc.toBytecode();
                return defineClass(name, bytecode, 0, bytecode.length);
            } catch (NotFoundException | CannotCompileException | IOException e) {
                throw new ClassNotFoundException(e.getMessage(), e);
            }
        }
    }
}
