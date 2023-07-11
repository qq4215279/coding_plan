/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

import com.mumu.common.annotation.MyAnno;
import com.mumu.common.pojo.IPerson;
import com.mumu.common.pojo.Person;
import com.mumu.common.pojo.User;
import com.mumu.common.pojo.UserGroup;
import com.mumu.java_base.enums.WeekEnum;
import com.mumu.java_base.jclass.OuterClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.Properties;

/**
 * ClassTest
 * 类对象api
 * @author liuzhen
 * @version 1.0.0 2023/7/6 22:04
 */
public class ClassTest {

    /**
     * Class.forName(String className) 返回与具有给定字符串名称的类或接口关联的类对象
     *
     * newInstance()  创建当前类表示的类的新实例
     *
     * isInstance(Object obj)  判断给定对象是否可以被转换为当前Class对象所表示的类或接口的实例。eg: user 是 Person类的实例，因此isInstance()方法返回true。
     * isAssignableFrom(类<?> cls)  判断一个类(当前类)是否是另一个类(cls)的父类或接口
     *
     * isInterface()  判断当前类是否为接口类型
     * isArray()  判断当前类是否表示数组类。
     * isPrimitive()  用于判断当前类是否为原始类型（primitive type）。原始类型包括byte、short、int、long、float、double 和 boolean、char。
     *
     * getSimpleName()  返回源代码中给出的基础类的简单名称。 如果基础类是匿名的，则返回空字符串。
     * getName()  返回此类对象表示的实体名称（类，接口，数组类，基本类型或void），作为String 。
     * getCanonicalName()  返回Java语言规范定义的基础类的规范名称。既指类的全限定名，其中包括包名和类名，并使用.分隔。eg: com.mumu.common.pojo.User
     * getTypeName()  返回此类型名称的信息字符串。 eg: List.class.getTypeName()  输出: "java.util.List"
     *
     * getModule()  返回一个 Module 对象
     * getPackageName()  返回完全限定的包名称。eg: com.mumu.common.pojo
     * getPackage()  获取表示类所在包的Package对象。Package对象提供了有关包的信息，如包名、实现的规范名称、实现的标题、实现的版本等。
     *
     * getClassLoader()  返回类的类加载器。 某些实现可能使用null来表示引导类加载器。 如果此类由引导类加载器加载，则此方法将在此类实现中返回null。如果此对象表示基本类型或void，则返回null。
     * getSuperclass()  获取表示当前类的直接父类的Class对象。
     * getGenericSuperclass()  用于获取表示当前类的直接父类的泛型类型信息
     * getTypeParameters()  获取表示类的泛型类型参数的数组。
     *
     * getInterfaces()  获取表示当前类实现的接口的Class对象数组。注：必须是当前实现的接口，其父类实现了接口不算！
     * getGenericInterfaces()  获取表示当前类实现的接口的泛型类型信息。返回 Type[] 数组！
     * getComponentType()  用于获取数组类型的组件类型。如果当前类不是数组类型，则返回null。如果当前类是数组类型，那么组件类型就是数组中存储的元素类型。eg: int[].class.getComponentType()  输出: int
     * getModifiers()  返回此类或接口的Java语言修饰符，以整数编码。 修饰符由Java虚拟机的常数为public, protected, private, final, static, abstract 和 interface; 它们应该使用 Modifier 类的方法解码。
     * getSigners()  获取此类的签名者。
     *
     * 类对象相关
     * getClasses()  返回一个数组，其中包含类对象，这些对象表示作为此类对象所表示的类的成员的所有公共类和接口。
     * getDeclaredClasses()   返回类对象的数组， 类对象反映声明为此类对象所表示的类的成员的所有类和接口。 这包括公共，受保护，默认（包）访问以及类声明的私有类和接口，但不包括继承的类和接口。
     * getConstructors()   返回一个包含构造器对象的数组， 构造器对象反映了此类对象所表示的类的所有公共构造函数。
     * getDeclaredConstructors()  返回构造器对象的数组， 构造器对象反映由此类对象表示的类声明的所有构造函数。 这些是公共，受保护，默认（包）访问和私有构造函数。
     * getConstructor(类<?>... parameterTypes)  返回一个构造器对象，该对象反映此类对象所表示的类的指定公共构造函数。
     * getDeclaredConstructor(类<?>... parameterTypes)  返回一个构造器对象，该对象反映此类对象所表示的类或接口的指定构造函数。
     * getMethods()   返回一个包含方法对象的数组， 方法对象反映此类对象所表示的类或接口的所有公共方法，包括由类或接口声明的那些以及从超类和超接口继承的那些。
     * getDeclaredMethods()   返回一个包含方法对象的数组， 方法对象反映此类对象表示的类或接口的所有已声明方法，包括公共，受保护，默认（包）访问和私有方法，但不包括继承的方法。
     * getMethod(String name, 类<?>... parameterTypes)  返回一个方法对象，该对象反映此类对象所表示的类或接口的指定公共成员方法。 name参数是String指定所需方法的简单名称。 parameterTypes参数是一个类对象的数组，以声明的顺序标识方法的形式参数类型。
     * getDeclaredMethod(String name, 类<?>... parameterTypes)  返回方法对象，该对象反映此类对象所表示的类或接口的指定声明方法。
     * getFields()   返回一个包含字段对象的数组， 字段对象反映此类对象所表示的类或接口的所有可访问公共字段。
     * getDeclaredFields()   返回字段对象的数组， 字段对象反映由此类对象表示的类或接口声明的所有字段。 这包括公共，受保护，默认（包）访问和私有字段，但不包括继承的字段。
     * getField(String name)  返回字段对象，该对象反映此类对象表示的类或接口的指定公共成员字段。 name参数是String指定所需字段的简单名称。
     * getDeclaredField(String name)  返回字段对象，该对象反映此类对象表示的类或接口的指定声明字段。 name参数是String ，它指定所需字段的简单名称。
     *
     * 类转换想换
     * cast(Object obj)  将一个对象强制转换为当前 Class 类型所表示的类型。 参数obj: 要转换的对象
     * asSubclass(类<U> clazz)  将当前类对象（表示一个类）转换为指定的类型，其中指定的类型是当前类对象的子类。该方法返回一个新的 Class 对象，表示当前类对象的子类。
     *
     * 注解相关：
     * isAnnotation()  当前类是否是注解类型。请注意，如果此方法返回true，则isInterface()也将返回true，因为所有注释类型也是接口。
     * isAnnotationPresent(类<? extends Annotation> annotationClass)  检查当前类是否标注了指定类型的注解。 此方法返回的真值等价于： getAnnotation(annotationClass) != null
     * getAnnotation(类<A> annotationClass)   获取当前类及其继承层次结构上指定类型的注解实例。无则为null。
     * getDeclaredAnnotation(类<A> annotationClass)  获取当前类上直接声明的指定类型的注解实例。无则为null。
     * getAnnotations()  获取当前类及其继承层次结构上所有注解实例的数组。
     * getDeclaredAnnotations()  获取当前类上直接声明的所有注解实例的数组。
     * getAnnotationsByType(类<A> annotationClass)  获取当前类及其继承层次结构上指定类型的注解实例的数组。如果没有与此元素关联的注释，则返回值是长度为0的数组。
     * getDeclaredAnnotationsByType(类<A> annotationClass)  获取当前类指定类型的注解实例的数组。如果没有与此元素关联的注释，则返回值是长度为0的数组。
     * getAnnotatedSuperclass()  获取表示当前类直接继承的父类的注解类型的AnnotatedType对象。AnnotatedType接口提供了对类型上的注解的访问和操作。
     * getAnnotatedInterfaces()  获取表示当前类实现的接口的注解类型的AnnotatedType对象数组。
     *
     * 枚举相关：
     * isEnum()  当且仅当此类在源代码中声明为枚举时返回true。
     * getEnumConstants()  返回此枚举类的元素，如果此Class对象不表示枚举类型，则返回null。
     *
     * 流相关
     * public InputStream getResourceAsStream(String name) 获取与给定名称关联的资源作为输入流。资源可以是类路径下的文件、配置文件或其他静态资源。该方法返回一个InputStream对象，可以用于读取资源的内容
     * public URL getResource(String name)  获取与给定名称关联的资源的URL对象。资源可以是类路径下的文件、配置文件或其他静态资源。该方法返回一个URL对象，可以用于获取资源的位置和访问资源的信息。
     * public ProtectionDomain getProtectionDomain()  获取当前类的保护域（ProtectionDomain）。保护域是与类关联的安全上下文信息，用于控制类的权限和访问限制。
     * public boolean desiredAssertionStatus()  获取当前类是否处于期望的断言状态。断言是一种在代码中添加的条件检查，用于在开发和调试过程中验证程序的正确性。
     *
     * 内部类：
     * isNestmateOf(类<?> c)  检查当前类是否与指定的类是嵌套关系（校验是否是外部类与内部类）。
     * getNestHost()  获取当前类所属的嵌套主机类的Class对象。
     * getNestMembers()  返回一个数组，其中包含类对象，这些对象表示作为此类对象所表示的类或接口所属的嵌套成员的所有类和接口。 该嵌套的nest host是数组的第0个元素。
     * isSynthetic()  （既校验是否是内部类）如果此类是合成类，则返回true; 否则返回false。合成类是在编译器生成的、在源代码中没有显式定义的类。
     * isAnonymousClass()  判断当前类是否是匿名内部类。当且仅当基础类是匿名类时，返回 true 。
     * isLocalClass()  判断当前类是否是局部内部类。当且仅当基础类是本地类时，返回 true 。
     * getEnclosingClass()  获取包含当前类的最外层类的Class对象。如果当前类是顶层类或匿名类，则返回null。如果当前类是一个内部类，则返回包含它的最外层类的Class对象。
     * getEnclosingConstructor()  如果此类对象表示构造函数中的本地或匿名类，则返回构造器对象，该对象表示基础类的直接封闭构造函数。 否则返回null 。则返回包含它的最外层类的构造函数的Constructor对象。
     * getEnclosingMethod()  如果此类对象表示方法中的本地或匿名类，则返回方法对象，该对象表示基础类的直接封闭方法。 否则返回null 。
     * getDeclaringClass()   获取定义当前类的类或接口的Class对象。如果当前类是顶层类或匿名类，则返回该类的Class对象。如果当前类是一个成员类（包括内部类和局部类），则返回包含它的外部类的Class对象。
     *
     */

    @Test
    public void test01() {
        Class<IPerson> iPersonClass = IPerson.class;

        UserGroup userGroup = new UserGroup();
        userGroup.setName("userGroup");
        User user = new User("mumu", "1234546", 18, 1);
        User user2 = new User("xiaojiang", "654321", 28, 0);
        userGroup.addUser(user);
        userGroup.addUser(user2);

        Class<? extends User> userClass = user.getClass();
        Class<? extends User> userClass2 = user2.getClass();

        System.out.println("含义：user 是 personClass 类对象的实例。isInstance(Object obj)  " + iPersonClass.isInstance(user));
        System.out.println("isInstance(Object obj)  " + userClass2.isInstance(iPersonClass));
        System.out.println("isAssignableFrom(类<?> cls)  " + iPersonClass.isAssignableFrom(userClass));
        System.out.println("personClass isInterface()  " + iPersonClass.isInterface());

        System.out.println("getName()  " + userClass.getName());
        System.out.println("getSimpleName()  " + userClass.getSimpleName());
        // 获取类的全限定名
        System.out.println("getCanonicalName()  " + userClass.getCanonicalName());
        // 获取类的完全限定名，包括泛型类型信息。
        System.out.println("getTypeName()  " + userClass.getTypeName());


        Class<?> superclass = userClass.getSuperclass();
        System.out.println("superclass name  " + superclass.getName());
        System.out.println("superclass simpleName  " + superclass.getSimpleName());

        Type genericSuperclass = userClass.getGenericSuperclass();
        System.out.println("getGenericSuperclass().getTypeName()  " + genericSuperclass.getTypeName());

        // 判断当前类修饰符
        int modifiers = userClass.getModifiers();
        if (java.lang.reflect.Modifier.isPublic(modifiers)) {
            System.out.println("The class is public");
        }

    }

    /**
     * getClassLoader()
     * @date 2023/7/7 17:13
     * @param
     * @return void
     */
    @Test
    public void getClassLoaderTest() throws ClassNotFoundException {
        // 可以创建任意类的对象，可以执行任意方法
        // 前提：不能改变该类的任何代码。可以创建任意类的对象，可以执行任意方法
       /* Person p = new Person();
        p.eat();

        Student stu = new Student();
        stu.sleep();*/

        // 1. 加载配置文件
        // 1.1 创建Properties对象
        Properties pro = new Properties();
        try {
            // 1.2 加载配置文件，转换为一个集合
            // 1.2.1 获取class目录下的配置文件
            ClassLoader classLoader = ClassTest.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("pro.properties");
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        // 3. 加载该类进内存
        Class cls = Class.forName(className);
        try {
            // 4. 创建对象
            Constructor constructor = cls.getConstructor();
            Object obj = constructor.newInstance();
            // Object obj = cls.newInstance();

            // 5. 获取方法对象
            Method method = cls.getMethod(methodName);

            // 6. 执行方法
            method.invoke(obj);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *  getResourceAsStream(String name)  name: 接收一个资源名称作为参数，可以是相对路径或绝对路径。
     *  getResource(String name)
     *  getProtectionDomain()
     *  desiredAssertionStatus()
     * @date 2023/7/7 17:37
     * @param
     * @return void
     */
    @Test
    public void resourceTest() {
        Class<ClassTest> classTestClass = ClassTest.class;

        // 1. getResourceAsStream()
        // 获取与给定名称关联的资源作为输入流
        InputStream inputStream = classTestClass.getResourceAsStream("myresource.txt");
        // 使用输入流读取资源内容
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 2. getResource(String name)
        // 获取与给定名称关联的资源的URL对象
        URL resourceUrl = classTestClass.getResource("myresource.txt");
        // 输出资源的URL
        System.out.println("Resource URL: " + resourceUrl);


        // 3. getProtectionDomain()
        // 获取与给定名称关联的资源的URL对象
        ProtectionDomain protectionDomain = classTestClass.getProtectionDomain();
        System.out.println("Protection domain: " + protectionDomain);


        // 4. desiredAssertionStatus()
        // 校验当前类是否启用了断言
        boolean assertionStatus = classTestClass.desiredAssertionStatus();
        System.out.println("Assertion status: " + assertionStatus);
    }

    /**
     * getInterfaces()
     * @date 2023/7/7 17:13
     * @param
     * @return void
     */
    @Test
    public void interfaceTest() {
        Class<Person> personClass = Person.class;
        Class<?>[] interfaces = personClass.getInterfaces();
        System.out.println("interface start ...");
        for (Class c : interfaces) {
            System.out.println(c.getSimpleName());
        }
        System.out.println("interface end ...");
    }

    /**
     * getModule() 获取 Module 对象，
     * Module对象相关操作api
     * 从Java 9开始引入，用于获取表示类所属模块的Module对象。
     * 模块化是Java 9中引入的一项重要特性，允许将代码划分为模块以实现更好的封装和依赖管理。该方法返回一个Module对象，表示类所属的模块。
     * @date 2023/7/7 15:48
     * @param
     * @return void
     */
    @Test
    public void moduleTest() {
        User user = new User("mumu", "1234546", 18, 1);
        Class<? extends User> userClass = user.getClass();

        // TODO Module

        Module classModule = userClass.getModule();
        if (classModule != null) {
            System.out.println("Module name: " + classModule.getName());
            System.out.println("Module is named: " + classModule.isNamed());
            // System.out.println("Module can read java.base: " + classModule.canRead("java.base"));
        } else {
            System.out.println("The class is not in any module.");
        }

    }

    /**
     *  获取 Package 对象，Package相关api操作
     * getPackage()  getPackageName()
     * @date 2023/7/7 14:13
     * @return void
     */
    @Test
    public void packageTest() {
        User user = new User("mumu", "1234546", 18, 1);
        Class<? extends User> userClass = user.getClass();

        // TODO Package

        Package classPackage = userClass.getPackage();

        System.out.println("getPackage()  " + userClass.getPackage());
        System.out.println("getPackageName()  " + userClass.getPackageName());

        if (classPackage != null) {
            System.out.println("Package name: " + classPackage.getName());
            System.out.println("Specification title: " + classPackage.getSpecificationTitle());
            System.out.println("Specification version: " + classPackage.getSpecificationVersion());
            System.out.println("Implementation title: " + classPackage.getImplementationTitle());
            System.out.println("Implementation version: " + classPackage.getImplementationVersion());
        } else {
            System.out.println("The class is not in any package.");
        }

    }

    /**
     *  isArray() 校验是否是数组类型
     * @date 2023/7/7 17:12
     * @param
     * @return void
     */
    @Test
    public void isArrayTest() {
        int[] arr = {3, 9, 8};
        Class<? extends int[]> intArrayClass = arr.getClass();
        System.out.println("isArray()  " + intArrayClass.isArray());


        Class<?> intComponentType = intArrayClass.getComponentType();
        // 输出: int
        System.out.println("Component type of int[]: " + intComponentType);

        Class<?> stringArrayClass = String[].class;
        Class<?> stringComponentType = stringArrayClass.getComponentType();
        // 输出: class java.lang.String
        System.out.println("Component type of String[]: " + stringComponentType);

    }

    /**
     * isPrimitive() 检验是否是原始类型操作api
     * @date 2023/7/7 17:11
     * @param
     * @return void
     */
    @Test
    public void isPrimitiveTest() {
        Class<Integer> intClass = int.class;
        Class<?> booleanClass = boolean.class;
        Class<?> stringClass = String.class;

        // 判断boolean是否为原始类型    输出: true
        boolean isBooleanPrimitive = booleanClass.isPrimitive();
        System.out.println("boolean is a primitive type: " + isBooleanPrimitive);

        // 判断int是否为原始类型    输出: true
        boolean isIntPrimitive = intClass.isPrimitive();
        System.out.println("int is a primitive type: " + isIntPrimitive);

        // 判断String是否为原始类型   输出: false
        boolean isStringPrimitive = stringClass.isPrimitive();
        System.out.println("String is a primitive type: " + isStringPrimitive);
    }

    /**
     * 内部类外部类操作
     * @date 2023/7/7 17:11
     * @param
     * @return void
     */
    @Test
    public void innerClassTest() {
        Class<OuterClass> outerClassClass = OuterClass.class;
        Class<OuterClass.MemberInnerClass> memberInnerClass = OuterClass.MemberInnerClass.class;
        // isNestmateOf() 是否是嵌套关系
        System.out.println("OuterClass与MemberInnerClass是否是嵌套关系：" + outerClassClass.isNestmateOf(memberInnerClass));
        System.out.println("MemberInnerClass与OuterClass是否是嵌套关系：" + memberInnerClass.isNestmateOf(outerClassClass));

        System.out.println("嵌套主机类(最外层类)：" + outerClassClass.getNestHost());
        System.out.println("嵌套主机类(最外层类)：" + memberInnerClass.getNestHost());

        System.out.println("所有的嵌套类有：" );
        Class<?>[] nestMembers = memberInnerClass.getNestMembers();
        for (Class nestClass : nestMembers) {
            System.out.println("      " + nestClass);
        }


        Class<OuterClass.StaticInnerClass> staticInnerClassClass = OuterClass.StaticInnerClass.class;
        boolean isInnerClassSynthetic  = staticInnerClassClass.isSynthetic();
        // TODO 目标输出 true  实际输出 false ？？？
        System.out.println("校验当前类是否是内部类。isSynthetic()  " + isInnerClassSynthetic);

        // 校验是否是匿名内部类
        System.out.println("是否是匿名内部类：" + staticInnerClassClass.isAnonymousClass());
        // 校验是否是局部内部类
        System.out.println("是否是局部内部类：" + staticInnerClassClass.isLocalClass());

        Class<?> enclosingClass = staticInnerClassClass.getEnclosingClass();
        // 获取最外层的类 class com.mumu.java_base.jclass.OuterClass
        System.out.println("Enclosing class of InnerClass: " + enclosingClass);


        Constructor<?> enclosingConstructor = memberInnerClass.getEnclosingConstructor();
        // TODO 目标输出: public OuterClass$InnerClass(OuterClass)   实际输出: null  ???
        System.out.println("Enclosing constructor of InnerClass: " + enclosingConstructor);

        // TODO 目标输出: public OuterClass$InnerClass(OuterClass)   实际输出: null  ???
        Method enclosingMethod = memberInnerClass.getEnclosingMethod();
        // 输出: public void OuterClass.outerMethod()
        System.out.println("Enclosing method of InnerClass: " + enclosingMethod);

        Class<?> declaringClass = memberInnerClass.getDeclaringClass();
        // 输出: class com.mumu.java_base.jclass.OuterClass
        System.out.println("Declaring class of InnerClass: " + declaringClass);
    }

    /**
     * 注解操作相关api
     * @date 2023/7/7 17:10
     * @param
     * @return void
     */
    @Test
    public void annotationTest() {
        Class<MyAnno> myAnnoClass = MyAnno.class;
        System.out.println("是否是注解类型  " + myAnnoClass.isAnnotation());
        System.out.println("是否是接口  " + myAnnoClass.isInterface());

        Class<Animal> animalClass = Animal.class;
        System.out.println("是否包含 MyAnno 注解：" + animalClass.isAnnotationPresent(MyAnno.class));

        MyAnno annotation = animalClass.getAnnotation(MyAnno.class);
        MyAnno declaredAnnotation = animalClass.getDeclaredAnnotation(MyAnno.class);
        if (annotation != null) {
            System.out.println("获取指定注解，打印注解中的值：" + annotation.toString());
        }

        Annotation[] annotations = animalClass.getAnnotations();
        Annotation[] declaredAnnotations = animalClass.getDeclaredAnnotations();
        System.out.println("获取所有的注解：" + annotations);

        MyAnno[] annotationsByType = animalClass.getAnnotationsByType(MyAnno.class);
        MyAnno[] declaredAnnotationsByType = animalClass.getDeclaredAnnotationsByType(MyAnno.class);
        System.out.println("获取当前类上，顶一顶多个 MyAnno 注解实例：" + annotations);

        // TODO
        Class<Dog> dogClass = Dog.class;
        AnnotatedType annotatedType = dogClass.getAnnotatedSuperclass();
        System.out.println("getAnnotatedSuperclass().getType()  " + annotatedType.getType());
        System.out.println("getAnnotatedSuperclass().getAnnotatedOwnerType()  " + annotatedType.getAnnotatedOwnerType());


        Class<User> userClass = User.class;
        AnnotatedType[] annotatedInterfaces = userClass.getAnnotatedInterfaces();
    }

    /**
     * 枚举相关api
     * @date 2023/7/7 17:10
     * @param
     * @return void
     */
    @Test
    public void enumTest() {
        Class<WeekEnum> weekEnumClass = WeekEnum.class;
        System.out.println("isEnum()  " + weekEnumClass.isEnum());
        WeekEnum[] enumConstants = weekEnumClass.getEnumConstants();
        for (WeekEnum weekEnum : enumConstants) {
            System.out.println(weekEnum);
        }
    }

    /**
     * cast(Object obj)  将一个对象强制转换为当前 Class 类型所表示的类型。 参数obj: 要转换的对象
     * asSubclass(类<U> clazz)  将当前类对象（表示一个类）转换为指定的类型，其中指定的类型是当前类对象的子类。该方法返回一个新的 Class 对象，表示当前类对象的子类。
     * @param
     * @return void
     * @date 2023/7/7 18:30
     */
    @Test
    public void castTest() {
        Object obj = new User();

        // 1. cast()
        Class<User> userClass = User.class;
        User myClass = userClass.cast(obj);
        myClass.eat();

        // 2. asSubclass(类<U> clazz)
        Class<Person> personClass = Person.class;
        Class subClass = userClass.asSubclass(personClass);
        // class com.mumu.common.pojo.User
        System.out.println(subClass);
    }

    public static void main(String[] args) {
        // 1. getGenericSuperclass() 示例
        getGenericSuperclassTest();

        // 2. getTypeParameters() 示例
        getTypeParametersTest();

        // 3. getGenericInterfaces() 示例
        getGenericInterfacesTest();
    }

    /**
     * getGenericSuperclass()方法
     * 是Class类中的一个方法，用于获取表示当前类的直接父类的泛型类型信息。
     * 与getSuperclass()方法不同，getGenericSuperclass()方法返回的是一个Type对象，可以用于获取父类的泛型参数类型。
     * 如果当前类没有显式指定父类或父类不是泛型类型，则该方法返回Class对象。下面是getGenericSuperclass()方法的使用示例：
     * @date 2023/7/7 16:12
     * @param
     * @return void
     */
    private static void getGenericSuperclassTest() {
        Class<?> dogClass = Dog.class;

        Type genericSuperclass = dogClass.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            // Generic superclass of Dog: class com.mumu.jdk_api.lang.Animal
            System.out.println("Generic superclass of Dog: " + parameterizedType.getRawType());
            // Type argument of Dog: class java.lang.String
            System.out.println("Type argument of Dog: " + typeArguments[0]);
        } else {
            System.out.println("Dog does not have a generic superclass.");
        }
    }

    /**
     * getTypeParameters()
     * 是Class类中的一个方法，从Java 5开始引入，用于获取表示类的泛型类型参数的数组。如果类没有声明泛型类型参数，则返回一个空数组。
     * 每个元素都是TypeVariable类型，表示一个泛型类型参数。
     * @date 2023/7/7 16:12
     * @param
     * @return void
     */
    private static void getTypeParametersTest() {
        Class<?> myClassClass = MyClass.class;

        System.out.println("Type name of MyClass2: " + myClassClass.getTypeName());

        TypeVariable<?>[] typeParameters = myClassClass.getTypeParameters();
        for (TypeVariable<?> typeVariable : typeParameters) {
            System.out.println("Type parameter name: " + typeVariable.getName());
            Type[] bounds = typeVariable.getBounds();
            System.out.print("Bounds: ");
            for (Type bound : bounds) {
                System.out.print(bound.getTypeName() + " ");
            }
            System.out.println();
        }
    }

    /**
     * getGenericInterfaces()
     * @date 2023/7/7 16:30
     * @param
     * @return void
     */
    private static void getGenericInterfacesTest() {
        Class<?> myClass2Class = MyClass2.class;
        Type[] genericInterfaces = myClass2Class.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                System.out.println("Generic interface: " + parameterizedType.getRawType());
                System.out.println("Type argument: " + typeArguments[0]);
            } else {
                System.out.println("Interface does not have generic type arguments.");
            }
        }
    }
}

@MyAnno("定义注解的值")
class Animal<T> {
}

class Dog extends Animal<String> {
}

class MyClass<T, U extends Number> {
    List<T> list;
    U value;
}

interface MyInterface<T> {
}

class MyClass2 implements MyInterface<String> {
}