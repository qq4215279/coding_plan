/**
 * package-info
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/10 13:55
 */
package com.mumu.java_tools.javaassist;

/**
 * javaassist 介绍
 * @date 2024/4/10 13:55
 *
 * study link: https://blog.csdn.net/liufang_imei/article/details/133078912
 *             https://www.jianshu.com/p/c76ffa3f8bc4?clicktime=1577316974
 *             https://www.cnblogs.com/hucn/p/3636912.html
 *             https://www.jianshu.com/p/334a148b420a
 *
 * JavaAssist（简称为 Javassist）是一个用于在 Java 程序运行期间操作字节码的开源框架。它提供了一组简单而强大的 API，可以让开发人员在运行时动态地修改、生成、转换 Java 类的字节码。
 * 以下是 JavaAssist 的主要特点和用途：
 * 1. 字节码操作: JavaAssist 允许开发人员直接操作字节码，而不需要了解字节码的细节。它提供了简洁的 API，可以在运行时动态修改类的结构、属性、方法等。
 * 2. 动态代理: JavaAssist 可以轻松地创建动态代理类，用于实现 AOP（面向切面编程）等功能。通过创建动态代理类，可以在方法执行前后插入额外的逻辑，实现日志记录、性能监控、事务管理等功能。
 * 3. 类的动态生成: JavaAssist 可以在运行时动态地生成新的 Java 类，而不需要依赖源代码。这对于一些需要动态生成类的场景非常有用，比如动态生成代理类、动态生成缓存类等。
 * 4. 类的动态加载: JavaAssist 可以在运行时动态加载新的类，而不需要重新启动 JVM。这对于某些需要动态加载类的场景非常有用，比如热部署、插件系统等。
 * 5. 字节码转换: JavaAssist 提供了丰富的字节码转换功能，可以对现有的类进行增强、修改、优化等操作。通过字节码转换，可以实现一些高级的功能，比如代码注入、方法拦截、代码混淆等。
 * 5. 运行时代码生成: JavaAssist 可以在程序运行期间动态生成新的代码片段，并将其编译成新的类。这对于某些需要动态生成代码的场景非常有用，比如动态生成 SQL 查询、动态生成模板代码等。
 *
 * 常用的 API：
 * 1. ClassPool: 是 JavaAssist 中最基本的类，用于管理类的字节码。它可以加载类的字节码、创建新的类、获取现有类的信息等。通过 ClassPool，可以在运行时动态地操作类的字节码。
 *      classPool = ClassPool.getDefault(): 获取默认的 ClassPool 实例，可以用于加载系统类路径中的类。
 *      classPool.importPackage("java.util.List");  导入包
 *      classPool.insertClassPath();
 *      获取ctClass:
 *          ctClass = classPool.get(className);  获取只能class对象。eg: CtClass stringCtClass = pool.get("java.lang.String");
 *          ctClass = classPool.makeClass("com.mumu.DynamicClass");  在 ClassPool 中创建新的类。
 *          ctClass = classPool.makeInterface("IDynamicClass");  在 ClassPool 中创建新接口/类/数组/枚举/注解等
 *              注：需要指定pubulc | final | static等，指定是数组|枚举|注解等，添加如下eg: ctClass.setModifiers(Modifier.PUBLIC | Modifier.STATIC)
 *
 * 2. CtClass: 是 ClassPool 中表示类的抽象类，用于表示一个类的字节码。它提供了丰富的方法，可以获取类的信息、修改类的结构、生成新的类等。
 *      添加新的成员变量：
 *          cClass.addField(CtField.make("private String str;",cClass));
 *          ctClass.addField(new CtField(CtClass.intType, name, ctClass));
 *      添加新的方法:
 *          ctClass.addMethod(src, ctClass);  将方法添加到新类中
 *      cMethod.setBody();  更改方法的内部实现
 *      ctClass.writeFile();  将 CtClass 对象表示的类写入文件系统，可以用于调试和验证生成的类。即将此更改将反映在原始类文件中。
 *      byte[] b = ctClass.toBytecode();  获取修改后的字节码的方法。
 *      Class<?> clazz = ctClass.toClass();  加载新类
 *
 * 3. CtMethod: 是 CtClass 中表示方法的类，用于表示类的一个方法。它提供了丰富的方法，可以获取方法的信息、修改方法的结构、生成新的方法等。
 *      eg: CtMethod ctMethod = CtNewMethod.make(src, ctClass); // src 为方法字符串
 *          CtMethod cMethod = cClass.getDeclaredMethod("addNumber");
 *          CtMethod cMethod = new CtMethod("方法返回值对应的ctClass", "方法名称", "方法参数对应的ctClass数组", "需要被附加的类/数组/枚举/注解等 对应的ctClass");
 *      ctMethod.insertBefore()、ctMethod.insertAfter(): 在方法的开头或结尾插入额外的字节码指令，实现方法的增强。
 *      ctMethod.setBody(): 设置方法的新的字节码内容，用于修改方法的实现。
 *
 *  写method的 body代码注意点：
 *      a.如果方法要使用参数的话，不能直接使用。 需要用 $1,$2,$3 来代替。 1-2-3代表前后顺序
 *             列如：方法上有 (int a,int b) a和b两个参数，那么在 setBody方法中要 使用 a 和 b,就需要用 $1=>a  $2=>b 使用$1,$2来代替 ， $0代码的是this
 *                      $args ：$args 指的是方法所有参数的数组类似Object[]，需要注意$args[0]对应的是$1,而不是$0
 *                      $r：指的是方法返回值的类型，主要用在类型的转型上
 *                      $w：$w代表一个包装类型。主要用在转型上。比如：Integer i = ($w)5;如果该类型不是基本类型，则会忽略
 *                      $type：返回结果值的类型
 *
 *          具体还有很多的符号可以使用，但是不同符号在不同的场景下会有不同的含义，所以在这里就不在赘述，可以看javassist 的说明文档。
 *          http://www.javassist.org/tutorial/tutorial2.html
 *      b.在写入某些对象时要加上包全路径名称
 *          列如： Date ， List ， Map ， 还有一些自定义的pojo类或者 service eg: List =>  java.util.List
 *          // 第三方自己定义的 service 和 pojo 可以在最开始通过
 *          classPool.importPackage("com.gdzy.JZFW.service"); 来进行导入
 *      c.在使用<>这样的泛型定义 标识时 要使用 /* */   /*将其包括起*/  // eg:  java.util.List<String> 要写成 java.util.List/*<String>*/
/**
 *      d.还有一个问题是我需改的class文件需要再重新上线到tomcat中运行，但是我每次修改完运行时都会报一个 线程没有正常结束 的错误，导致tomcat启动不了，最后发现是某些类型的定义不能使用 引用类型 只能 使用 基本类型
 *          列如： Float，Long
 *          不能使用 Float.valueOf() 只能使用 Float.parseFloat() 来进行转换类型
 *
 *
 * 4. CtField: 是 CtClass 中表示字段的类，用于表示类的一个字段。它提供了丰富的方法，可以获取字段的信息、修改字段的结构、生成新的字段等。
 *      CtField.setModifiers(): 设置字段的修饰符，用于修改字段的访问权限。
 *
 * 5. CtConstructor: 是 CtClass 中表示构造函数的类，用于表示类的一个构造函数。它提供了丰富的方法，可以获取构造函数的信息、修改构造函数的结构、生成新的构造函数等。
 *      CtConstructor.insertBeforeBody()、CtConstructor.insertAfterBody(): 在构造函数的开头或结尾插入额外的字节码指令，实现构造函数的增强。
 *
 *
 */