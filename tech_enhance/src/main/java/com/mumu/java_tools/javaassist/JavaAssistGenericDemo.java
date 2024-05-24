package com.mumu.java_tools.javaassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * JavaAssistGenericDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/10 18:06
 */
public class JavaAssistGenericDemo {

    public static void main(String[] args) {
        demo();

        demo2();
    }

    public static void demo() {
        // 获取系统Java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        /**
         * public class MyClass<T> {
         *     T myField;
         *
         *     public <T> T myMethod(T var1) {
         *         return null;
         *     }
         *
         *     public MyClass() {
         *     }
         * }
         */
        try {
            ClassPool pool = ClassPool.getDefault();

            // 创建带泛型的类 MyClass<T>
            CtClass ctClass = pool.makeClass("MyClass");
            ctClass.setGenericSignature("<T:Ljava/lang/Object;>Ljava/lang/Object;");

            // 创建泛型字段 T myField;
            CtField ctField = new CtField(pool.get("java.lang.Object"), "myField", ctClass);
            ctField.setGenericSignature("TT;"); // TT; 表示 T 类型
            ctClass.addField(ctField);

            // 创建泛型方法 T myMethod(T t);
            CtMethod ctMethod = new CtMethod(pool.get("java.lang.Object"), "myMethod", new CtClass[]{pool.get("java.lang.Object")}, ctClass);
            ctMethod.setGenericSignature("<T:Ljava/lang/Object;>(TT;)TT;"); // 表示参数和返回值都是 T 类型
            ctMethod.setBody("{ return null; }"); // 设置一个简单的方法体
            ctClass.addMethod(ctMethod);

            // 将生成的类文件写入磁盘
            ctClass.writeFile(JavaAssistDemo.CLASSES_OUT_DIR); // 指定输出目录

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demo2() {
        /**
         * import java.util.List;
         *
         * public class MyClass2<T extends Number> {
         *     List<? extends Number> numbers;
         *
         *     public void myMethod(List<? super Integer> var1) {
         *     }
         *
         *     public MyClass2() {
         *     }
         * }
         */
        try {
            ClassPool pool = ClassPool.getDefault();

            // 创建一个泛型类 MyClass<T extends Number>
            CtClass ctClass = pool.makeClass("MyClass2");
            ctClass.setGenericSignature("<T:Ljava/lang/Number;>Ljava/lang/Object;");

            // 创建一个带有上限的泛型字段 List<? extends Number> numbers;
            CtField upperBoundedField = new CtField(pool.get("java.util.List"), "numbers", ctClass);
            upperBoundedField.setGenericSignature("Ljava/util/List<+Ljava/lang/Number;>;");
            ctClass.addField(upperBoundedField);

            // 创建一个带有下限的泛型方法 void myMethod(List<? super Integer> list);
            CtMethod lowerBoundedMethod = new CtMethod(CtClass.voidType, "myMethod", new CtClass[]{pool.get("java.util.List")}, ctClass);
            lowerBoundedMethod.setGenericSignature("(Ljava/util/List<-Ljava/lang/Integer;>;)V");
            lowerBoundedMethod.setBody("{}");
            ctClass.addMethod(lowerBoundedMethod);

            // 将生成的类文件写入磁盘
            ctClass.writeFile(JavaAssistDemo.CLASSES_OUT_DIR); // 指定输出目录

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
