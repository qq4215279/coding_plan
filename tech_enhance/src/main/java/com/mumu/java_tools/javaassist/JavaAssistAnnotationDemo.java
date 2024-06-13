package com.mumu.java_tools.javaassist;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.EnumMemberValue;

import java.io.IOException;

/**
 * JavaAssistAnnotationDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/10 18:27
 */
public class JavaAssistAnnotationDemo {
    public static void main(String[] args) {
        // createAnnotation();
        //
        useAnnotation();

    }

    public static void useAnnotation() {
        try {
            ClassPool pool = ClassPool.getDefault();
            // 创建一个新类
            CtClass ctClass = pool.makeClass("MyAnnotatedClass");

            // 为类添加注解
            ClassFile classFile = ctClass.getClassFile();
            ConstPool constPool = classFile.getConstPool();

            // 创建一个注解
            AnnotationsAttribute classAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation classAnnotation = new Annotation("java.lang.Deprecated", constPool);
            classAttr.addAnnotation(classAnnotation);

            // 将注解添加到类
            classFile.addAttribute(classAttr);

            // 创建一个字段
            CtField ctField = new CtField(CtClass.intType, "myField", ctClass);

            // 为字段添加注解
            AnnotationsAttribute fieldAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation fieldAnnotation = new Annotation("javax.annotation.Resource", constPool);
            fieldAttr.addAnnotation(fieldAnnotation);

            // 将注解添加到字段
            ctField.getFieldInfo().addAttribute(fieldAttr);
            ctClass.addField(ctField);

            // 创建一个方法
            CtMethod ctMethod = CtNewMethod.make("public void myMethod() {}", ctClass);

            // 为方法添加注解
            AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation methodAnnotation = new Annotation("org.junit.Test", constPool);
            methodAttr.addAnnotation(methodAnnotation);

            // 将注解添加到方法
            ctMethod.getMethodInfo().addAttribute(methodAttr);
            ctClass.addMethod(ctMethod);

            // 将生成的类文件写入磁盘
            ctClass.writeFile(JavaAssistDemo.CLASSES_OUT_DIR); // 指定输出目录

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个注解
     * @return void
     * @date 2024/4/10 19:44
     */
    public static void createAnnotation() {
        try {
            ClassPool pool = ClassPool.getDefault();

            // 创建一个新的注解接口
            CtClass ctAnnotation = pool.makeInterface("MyCustomAnnotation");
            // 设置注解标记
            ctAnnotation.setModifiers(ctAnnotation.getModifiers() | Modifier.ANNOTATION);

            // 添加注解的成员"value"，默认类型是String，默认值是"default"
            CtClass stringCtClass = pool.get("java.lang.String");
            CtMethod valueMethod = new CtMethod(stringCtClass, "value", null, ctAnnotation);
            valueMethod.setModifiers(Modifier.PUBLIC);
            ctAnnotation.addMethod(valueMethod);

            // 创建Retention注解，指定注解的保留策略（Runtime）
            AnnotationsAttribute attr = new AnnotationsAttribute(ctAnnotation.getClassFile().getConstPool(), AnnotationsAttribute.visibleTag);
            Annotation retention = new Annotation("java.lang.annotation.Retention", ctAnnotation.getClassFile().getConstPool());

            EnumMemberValue retentionPolicyValue = new EnumMemberValue(ctAnnotation.getClassFile().getConstPool());
            retentionPolicyValue.setType("java.lang.annotation.RetentionPolicy");
            retentionPolicyValue.setValue("RUNTIME");
            retention.addMemberValue("value", retentionPolicyValue);
            attr.addAnnotation(retention);

            // 将Annotation属性添加到注解类上
            ctAnnotation.getClassFile().addAttribute(attr);

            // 保存注解类到文件
            ctAnnotation.writeFile(JavaAssistDemo.CLASSES_OUT_DIR);

        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }

}
