## 学习笔记

### 主要内容
- JVM基础知识
- 字节码技术
- 类加载器
- 内存模型
- 启动参数

### 作业

#### 作业1(选)

写一个简单的Hello.java，里面涉及到基本类型，四则运算，if和for,分析一下对应的字节码


分析:

#### 作业2(必)
##### 题
自定义一个Classloader,加载一个Hello.xlaas文件，执行hello文件，此文件是一个Hello.class
文件所有字节处理后的文件。

##### 解

- [HelloClassLoader](HelloClassLoader.java)


#### 作业3(必)
##### 题
画一张图,展示Xmx、Xms、Xmn、Meta、DirectMemory、Xss这些内存参数的关系

##### 解

![JVM参数结构图](JVM参数结构图.png)

- Xmx 最大Heap的大小，默认为物理内存的1/4
- Xms 初始的Heap的大小 (在很多情况下，-Xms和-Xmx设置成一样的。这么设置，是因为当Heap不够用时，如果不一致，会发生内存抖动，影响程序运行稳定性。)
- Xmn 年轻代大小 通过这个值也可以得到老生代的大小：-Xmx减去-Xmn
- Meta metaspace大小
- DirectMemory 堆外内存大小
- Xss 设置每个线程可使用的内存大小，即栈的大小。

https://yq.aliyun.com/articles/268842



> Xss: 在相同物理内存下，减小这个值能生成更多的线程，当然操作系统对一个进程内的线程数还是有限制的，不能无限生成。
> 线程栈的大小是个双刃剑，如果设置过小，可能会出现栈溢出，特别是在该线程内有递归、大的循环时出现溢出的可能性更大，
> 如果该值设置过大，就有影响到创建栈的数量，如果是多线程的应用，就会出现内存溢出的错误。


#### 作业4(选)

检查一下自己维护的业务系统的JVM配置，用jstat和jstack、jmap查看一下情况，独立分析一下
大概情况，思考有什么不合理的地方可以改进。