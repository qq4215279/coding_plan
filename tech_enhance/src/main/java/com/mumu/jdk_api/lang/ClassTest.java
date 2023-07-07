/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

/**
 * ClassTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/6 22:04
 */
public class ClassTest {

    /**
     * Class.forName(String className)  返回与具有给定字符串名称的类或接口关联的类对象
     *
     * newInstance()  创建此类对象表示的类的新实例
     * isInstance(Object obj)  确定指定的Object是否与此类表示的对象分配兼容
     * isAssignableFrom(类<?> cls)  确定此类对象表示的类或接口是否与指定的类参数表示的类或接口相同，或者是它的超类或超接口。 如果是这样，它返回true ; 否则返回false 。 如果此类对象表示基本类型，如果指定的类参数恰好是此类对象，则此方法返回true ; 否则返回false 。
     * isInterface()  确定指定的 类对象是否表示接口类型。
     * isArray()  确定此 类对象是否表示数组类。
     * isPrimitive()  确定指定的类对象是否表示基本类型。
     * isAnnotation()  如果此类对象表示注释类型，则返回true。 请注意，如果此方法返回true，则isInterface()也将返回true，因为所有注释类型也是接口。
     * isSynthetic()  如果此类是合成类，则返回true ; 否则返回false 。
     *
     * getName()  返回此类对象表示的实体名称（类，接口，数组类，基本类型或void），作为String 。
     * getSimpleName()  返回源代码中给出的基础类的简单名称。 如果基础类是匿名的，则返回空字符串。
     * getTypeName()  返回此类型名称的信息字符串。
     * getModule()  返回此类或接口所属的模块。 如果此类表示数组类型，则此方法返回元素类型的模块 。 如果此类表示基本类型或void，则返回java.base模块的模块对象。
     * getCanonicalName()  返回Java语言规范定义的基础类的规范名称。
     * getClassLoader()  返回类的类加载器。 某些实现可能使用null来表示引导类加载器。 如果此类由引导类加载器加载，则此方法将在此类实现中返回null。如果此对象表示基本类型或void，则返回null。
     * getTypeParameters()  返回的数组TypeVariable对象表示通过此表示的一般声明声明的类型变量GenericDeclaration目的，在声明的顺序。
     * getSuperclass()  返回表示此类表示的实体的直接超类（类，接口，基本类型或void）的类 。
     * getGenericSuperclass()  返回Type表示此所表示的实体（类，接口，基本类型或void）的直接超类类 。
     * getPackage()  获取此类的包。
     * getPackageName()  返回完全限定的包名称。
     * getInterfaces()  返回由此对象表示的类或接口直接实现的接口。
     * getGenericInterfaces()  返回表示由此对象表示的类或接口直接实现的接口的Type 。
     * getComponentType()  返回表示数组的组件类型的类 。 如果此类不表示数组类，则此方法返回null。
     * getModifiers()  返回此类或接口的Java语言修饰符，以整数编码。 修饰符由Java虚拟机的常数为public ， protected ， private ， final ， static ， abstract和interface ; 它们应该使用Modifier类的方法解码。
     * getSigners()  获取此类的签名者。
     *
     * getEnclosingMethod()  如果此类对象表示方法中的本地或匿名类，则返回方法对象，该对象表示基础类的直接封闭方法。 否则返回null 。
     * getEnclosingConstructor()  如果此类对象表示构造函数中的本地或匿名类，则返回构造器对象，该对象表示基础类的直接封闭构造函数。 否则返回null 。
     * getDeclaringClass()   如果此类对象表示的类或接口是另一个类的成员，则返回表示声明它的类的类对象。 如果此类或接口不是任何其他类的成员，则此方法返回null。 如果此类对象表示数组类，基本类型或void，则此方法返回null。
     * getEnclosingClass()  返回基础类的直接封闭类。 如果底层类是顶级类，则此方法返回null 。
     *
     * isAnonymousClass()  当且仅当基础类是匿名类时，返回 true 。
     * isLocalClass()  当且仅当基础类是本地类时，返回 true 。
     * isLocalClass()  当且仅当基础类是本地类时，返回 true 。
     *
     * getClasses()  返回一个数组，其中包含类对象，这些对象表示作为此类对象所表示的类的成员的所有公共类和接口。
     * getFields()   返回一个包含字段对象的数组， 字段对象反映此类对象所表示的类或接口的所有可访问公共字段。
     * getMethods()   返回一个包含方法对象的数组， 方法对象反映此类对象所表示的类或接口的所有公共方法，包括由类或接口声明的那些以及从超类和超接口继承的那些。
     * getConstructors()   返回一个包含构造器对象的数组， 构造器对象反映了此类对象所表示的类的所有公共构造函数。
     * getField(String name)  返回字段对象，该对象反映此类对象表示的类或接口的指定公共成员字段。 name参数是String指定所需字段的简单名称。
     * getMethod(String name, 类<?>... parameterTypes)  返回一个方法对象，该对象反映此类对象所表示的类或接口的指定公共成员方法。 name参数是String指定所需方法的简单名称。 parameterTypes参数是一个类对象的数组，以声明的顺序标识方法的形式参数类型。
     * getConstructor(类<?>... parameterTypes)  返回一个构造器对象，该对象反映此类对象所表示的类的指定公共构造函数。
     *
     * getDeclaredClasses()   返回类对象的数组， 类对象反映声明为此类对象所表示的类的成员的所有类和接口。 这包括公共，受保护，默认（包）访问以及类声明的私有类和接口，但不包括继承的类和接口。
     * getDeclaredFields()   返回字段对象的数组， 字段对象反映由此类对象表示的类或接口声明的所有字段。 这包括公共，受保护，默认（包）访问和私有字段，但不包括继承的字段。
     * getDeclaredMethods()   返回一个包含方法对象的数组， 方法对象反映此类对象表示的类或接口的所有已声明方法，包括公共，受保护，默认（包）访问和私有方法，但不包括继承的方法。
     * getDeclaredConstructors()  返回构造器对象的数组， 构造器对象反映由此类对象表示的类声明的所有构造函数。 这些是公共，受保护，默认（包）访问和私有构造函数。
     * getDeclaredField(String name)  返回字段对象，该对象反映此类对象表示的类或接口的指定声明字段。 name参数是String ，它指定所需字段的简单名称。
     * getDeclaredMethod(String name, 类<?>... parameterTypes)  返回方法对象，该对象反映此类对象所表示的类或接口的指定声明方法。
     * getDeclaredConstructor(类<?>... parameterTypes)  返回一个构造器对象，该对象反映此类对象所表示的类或接口的指定构造函数。
     *
     * public InputStream getResourceAsStream(String name) 查找具有给定名称的资源。
     * public URL getResource(String name)  查找具有给定名称的资源。
     * public ProtectionDomain getProtectionDomain()  返回ProtectionDomain 。
     * public boolean desiredAssertionStatus()  如果要在调用此方法时初始化，则返回将分配给此类的断言状态。
     *
     * isEnum()  当且仅当此类在源代码中声明为枚举时返回true。
     * getEnumConstants()  返回此枚举类的元素，如果此Class对象不表示枚举类型，则返回null。
     *
     * cast(Object obj)  将对象 类为此 类对象表示的类或接口。 参数  obj - 要转换的对象
     * asSubclass(类<U> clazz)  转换此类对象以表示由指定的类对象表示的类的子类。
     *
     * public <A extends Annotation> A getAnnotation(类<A> annotationClass)   如果此元素上存在指定注释类型，则此元素的注释，否则为null
     * isAnnotationPresent(类<? extends Annotation> annotationClass)  如果此元素上存在指定类型的注释，则返回true，否则返回false。 此方法主要用于方便地访问标记注释。 此方法返回的真值等价于： getAnnotation(annotationClass) != null
     * getAnnotationsByType(类<A> annotationClass)  返回与此元素关联的注释。 如果没有与此元素关联的注释，则返回值是长度为0的数组。
     * getAnnotations()  返回此元素上存在的注释。
     * getDeclaredAnnotation(类<A> annotationClass)  getDeclaredAnnotation​(类<A> annotationClass)
     * getDeclaredAnnotationsByType(类<A> annotationClass)  果此类注释直接存在或间接存在 ，则返回指定类型的此元素的注释。
     * getDeclaredAnnotations()  返回直接出现在此元素上的注释。 此方法忽略继承的注释。
     * getAnnotatedSuperclass()  返回一个AnnotatedType对象，该对象表示使用类型来指定此类对象所表示的实体的超类。
     * getAnnotatedInterfaces()  返回AnnotatedType对象的数组， AnnotatedType对象表示使用类型来指定此类对象所表示的实体的超类 。
     *
     * getNestHost()  返回此类对象所代表的类或接口所属的nest的嵌套主机。
     * isNestmateOf(类<?> c)  确定给定的类是否是此类对象所表示的类或接口的类 。 如果两个类或接口具有相同的nest host，则它们是嵌套的 。
     * getNestMembers()  返回一个数组，其中包含类对象，这些对象表示作为此类对象所表示的类或接口所属的嵌套成员的所有类和接口。 该嵌套的nest host是数组的第0个元素。
     *
     */

}
