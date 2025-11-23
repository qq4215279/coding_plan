/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

/**
 * package-info
 *
 * @author liuzhen
 * @version 1.0.0 2025/6/22 16:59
 */
package com.mumu.java_tools.arthas;

/**
 *
 * 一、ognl 使用：ognl '@全限定类名@静态方法(参数1,参数2,...)'
 *           ognl '#instance=@全限定类名@静态方法(参数1,参数2,...), #instance.fieldName'
 * 选项参数
 *   -c <hashcode>: 指定 ClassLoader 的 hashcode
 *   -x <depth>: 设置结果展开层级 (默认 1)
 *   --classLoaderClass: 指定 ClassLoader 的类名
 * eg：
 *   1. 查看静态变量：
 *      ognl '@java.lang.System@out'
 *      ognl '#springContext=@com.xxx.ApplicationContextProvider@context,#springContext.getBean("userService")'
 *      ognl '@java.lang.System@getProperty("java.home")'
 *      ognl '@com.mumu.java_tools.arthas.MathGame@random'  # 查看静态 random 变量
 *   2. 调用静态方法：
 *      ognl '@java.lang.System@currentTimeMillis()'
 *      ognl '@java.lang.Math@max(10, 20)'
 *      ognl '@java.lang.System@getProperty("user.dir")'  # 获取系统属性
 *      MathGame操作：
 *        ognl '#game=@com.mumu.java_tools.arthas.MathGame@new(), #game.illegalArgumentCount'  # 创建实例
 *        ognl '@com.mumu.java_tools.arthas.MathGame@print(72, {2,2,2,3,3})'  # 调用静态方法打印质因数分解结果
 *        ognl '#game=@com.mumu.java_tools.arthas.MathGame@new(), #game.illegalArgumentCount'  # 获取实例的非法参数计数
 *        ognl '#game=@com.mumu.java_tools.arthas.MathGame@new(), #game.illegalArgumentCount=5'  # 修改非法参数计数
 *        ognl '#game=@com.mumu.java_tools.arthas.MathGame@new(), #game.primeFactors(72)'  # 创建新实例并计算质因数
 *        ognl '#game=@com.mumu.java_tools.arthas.MathGame@new(), #game.run(), "执行成功"'  # 模拟run()方法调用（需捕获异常）
 *   3. 查看对象属性：ognl '#obj.fieldName'
 *   4. 调用实例方法：ognl '#obj.methodName(arg1, arg2)'
 *   5. 查看类加载器：ognl -x 2 '#classLoader'
 *   6. 被final修饰的静态变量赋值，不能通过=直接赋值，需要采用反射的方式设置值
 * 			ognl '#c=@com.cxx.hf.servercore.constant.YjConstant@class,#f=#c.getDeclaredField("useCacheModelData"),#f.set(#c,false)'
 * 		修改private静态属性,需要在反射时调用方法 setAccessible ,使 private 特殊转化为 public
 * 			ognl '#c=@@com.cxx.hf.servercore.constant.YjConstant@class,#f=#c.getDeclaredField("useCacheModelData"),#f.setAccessible(true),#f.set(#c,false)'
 *
 *
 * @ 与 #  开头有什么区别：
 * @：用于访问 静态成员（静态变量或静态方法）
 * #: 表达式则用于访问 实例成员
 *
 *
 * 二、vmtool： 是 Arthas 中一个非常强大的命令，它可以直接操作 JVM 中的对象，包括获取实例、调用方法、修改字段值等。
 *          相比 ognl，vmtool 提供了更底层的 JVM 操作能力，特别适合在复杂调试场景中使用。
 * 基本语法: vmtool [--action <action>] [--className <className>] [--express <express>] [--limit <limit>] [--options]
 * 常用选项
 *   --action	执行的操作类型，如 getInstances/express
 *   --className	目标类的全限定名
 *   --express	要执行的OGNL表达式
 *   --limit	限制返回的实例数量
 *   --options	附加选项，如 gc
 *
 * 主要功能
 * 1. 获取类的所有实例 (--action getInstances)
 *   eg: 获取某个类的所有实例: # 查看当前 JVM 中 MathGame 的所有实例
 *      vmtool --action getInstances --className com.mumu.java_tools.arthas.MathGame  --limit 10 # 并限制返回数量
 *
 * 2. 执行表达式 (--action express)
 *   eg: 对获取的实例执行OGNL表达式：vmtool --action express --className com.mumu.java_tools.arthas.MathGame --express 'instances[0].generateQuestion()'
 *     # 查看当前错误计数: vmtool --action express --className com.mumu.java_tools.arthas.MathGame --express 'instances[0].illegalArgumentCount'
 *     # 修改错误计数（例如设为 10）: vmtool --action express --className com.mumu.java_tools.arthas.MathGame --express 'instances[0].illegalArgumentCount=10'
 *     # 手动计算一个数的质因数分解：vmtool --action express --className com.mumu.java_tools.arthas.MathGame --express 'instances[0].primeFactors(72)'
 *
 * 3. 强制GC后获取实例 (--action getInstances --options gc)
 *   eg: vmtool --action getInstances --className com.example.MyClass --options gc
 *
 *
 * ognl 与 vmtool 的对比
 * 特性	                            OGNL	                            Vmtool
 * 静态成员操作	                   ✅ 直接支持	                    ❌ 需间接调用
 * 实例获取	                       只能创建新实例	                    ✅ 可获取运行中实例
 * 执行效率	                        更高	                            较低（涉及JVM TI调用）
 * 适用场景	                        简单表达式/静态操作	            复杂调试/运行时对象操作
 *
 *
 * 获取对象实例方法：
 *  1. vmtool
 *    # 获取所有实例（可能返回多个）vmtool --action getInstances --className com.example.MyClass
 *    # 获取单个实例并操作: vmtool --action getInstances --className com.example.MyClass --express 'instances[0].someMethod()'
 *
 * 2. vmtool + ognl TODO 执行失败
 *   2.1. 先获取对象的hashCode: vmtool --action getInstances --className com.mumu.java_tools.arthas.MathGame --express 'instances[0].hashCode()' // @Integer[105709034]
 *   2.2. 通过ognl获取对象：ognl '#game=@java.lang.management.ManagementFactory@getRuntimeMXBean().getObjectName(105709034), #game.illegalArgumentCount'
 *
 *
 *
 * 三、watch 是 Arthas 中最强大的命令之一，用于观察方法调用时的入参、返回值、异常和对象属性变化。它特别适合动态调试运行中的 Java 程序，无需修改代码或重启应用。
 * 基本语法：watch [options] 类全限定名 方法名 [观察表达式] [条件表达式]
 * 常用选项
 *   选项	                        说明	                                    示例
 *   -b	                        方法调用前观察	                watch -b MathGame primeFactors params
 *   -e	                        方法异常时观察	                watch -e MathGame primeFactors throwExp
 *   -s	                        方法返回后观察(默认)             	watch -s MathGame primeFactors returnObj
 *   -f	                        方法结束(正常返回和异常)后观察	    watch -f MathGame primeFactors returnObj
 *   -x	                        指定输出结果的属性遍历深度	        watch -x 3 MathGame primeFactors params
 *   -n	                        观察次数限制	                    watch -n 5 MathGame primeFactors params
 *   --exclude-class-pattern	排除特定类	                    watch --exclude-class-pattern java.util.*
 *
 * 观察表达式说明
 *   表达式	                        含义	                                    示例
 *   params	                    方法参数数组	                    params[0] 第一个参数
 *   returnObj	                方法返回值	                    returnObj.size()
 *   throwExp	                抛出的异常	                    throwExp.getMessage()
 *   target	                    当前对象实例	                    target.illegalArgumentCount
 *   #cost	                    方法执行耗时(ms)	                #cost>100
 *   #methodName	            当前方法名	                    #methodName
 *
 * eg:
 * # 观察方法入参和返回值(条件：参数小于0的调用)  watch com.mumu.java_tools.arthas.MathGame primeFactors "{params, returnObj}" "params[0]<0" -x 2
 * # 观察illegalArgumentCount字段变化  watch com.mumu.java_tools.arthas.MathGame primeFactors "{params,target.illegalArgumentCount}" -x 2
 * # 捕获方法抛出的异常  watch com.mumu.java_tools.arthas.MathGame primeFactors "{params, throwExp}" -e
 * # 显示方法执行时间(ms)切耗时超过5ms的调用  watch com.mumu.java_tools.arthas.MathGame primeFactors "{params, #cost}" "#cost>5"
 *
 *
 *
 * 四、tt (Time Tunnel) 是 Arthas 中用于记录和回放方法调用的强大命令，可以看作是"方法调用的时光隧道"。它特别适合以下场景：
 *   记录特定方法的调用参数和返回值  回放某次方法调用（复现问题）  观察方法调用时的对象状态变化
 * 基本语法：tt [options] [command] [arguments]
 * 常用选项
 *   选项	                        说明	                                    示例
 *   -t	                         开始记录方法调用	                tt -t MathGame primeFactors
 *   -n	                         记录次数限制(默认记录100条)	    tt -t -n 5 MathGame primeFactors
 *   -l	                         列出所有记录	                    tt -l
 *   -i	                         查看指定索引记录                  tt -i 1000
 *   -p	                         回放记录	                    tt -i 1000 -p
 *   --delete	                 删除记录	                    tt --delete 1000
 *   -w	                         观察表达式	                    tt -i 1000 -w 'params'
 *   -e	                         只记录异常调用	                tt -t -e MathGame primeFactors
 *   --replay-times	             回放次数	                    tt -i 1000 -p --replay-times 3
 *   --replay-interval	         回放间隔(ms)	                tt -i 1000 -p --replay-interval 1000
 *
 * 核心功能
 * 1. 记录方法调用 (tt -t)
 *  eg: tt -t com.mumu.java_tools.arthas.MathGame primeFactors -n 5 -w '{params, returnObj}'   # 记录MathGame.primeFactors方法的5次调用，显示参数和返回值
 *      tt -t com.mumu.java_tools.arthas.MathGame primeFactors -e -w '{params, throwExp}'   # 只记录抛出异常的调用
 *      tt -t com.mumu.java_tools.arthas.MathGame primeFactors 'params[0]>100'   # 只记录参数大于100的调用
 * 2.查看记录列表 (tt -l)
 *  eg: tt -l
 * 3. 查看详细记录 (tt -i)
 *  eg: tt -i 1000  # 查看索引为1000的记录详情
 *      tt -i 1000 -w 'target.illegalArgumentCount'  # 查看索引1000调用时的illegalArgumentCount值
 * 4. 回放方法调用 (tt -i -p)
 *  eg: tt -i 1000 -p
 *      tt -i 1000 -p --params 72  # 修改参数后回放（例如把参数改为72）
 *      tt -i 1000 -p --replay-times 3 --replay-interval 1000   # 回放3次，每次间隔1秒
 *
 * 与watch命令的区别
 *   特性	                         tt 命令	                                watch 命令
 *   记录能力	                 可以永久保存记录	                            仅实时观察
 *   回放能力	                 支持修改参数回放	                            不支持
 *   性能影响	                 记录时影响小	                                实时观察影响大
 *   使用场景	                 事后分析、问题复现	                        实时调试、动态观察
 *   对象状态	                 可查看历史状态	                            只能查看当前状态
 *
 *
 * 五、sc:
 *  eg: 查找类加载信息：sc -d com.mumu.java_tools.arthas.MathGame
 *    sc -l  扫描目标JVM类信息
 *
 * 六、sm:
 *  eg: 查找方法：sm com.mumu.java_tools.arthas.MathGame
 *
 *
 * 七、jad: jad 全限定类名  反编译指定已加载类的源码
 *
 */