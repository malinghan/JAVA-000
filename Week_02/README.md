## 学习笔记

### 主要内容
- JVM基础知识
- 字节码技术
- 类加载器
- 内存模型
- 启动参数

### 作业1

写一段对于不同GC的总结

#### 回答

##### 串行化GC
启动参数
```$xslt
java -XX:+UseSerialGC  -Xmx512m  -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
```
在内存较小时，会发生很多fullgc,同时服务整体不可用。

原因是：串行化gc期间，会完全占用业务线程资源，用于垃圾回收
##### 并行gc
jdk8的默认gc策略,在不进行垃圾回收时，业务线程完全不收垃圾回收器的影响，所以能做到吞吐量优先

默认为cpu
##### cms和g1
在分配内存较小时 cms可能比g1更高效
但是，由于g1的内存划分规则，当内存越大时，g1比cms越高效

我的测试结果

```
wrk -c 40 -d30s http://localhost:8088/api/hello

2 threads and 40 connections 30s

512m   g1:28214.74/3.37MB   cms:37977.57/4.53MB    cms略胜一筹
1g     g1:37737.10/4.51MB   cms:42087.51/5.02MB  cms略胜一筹
2g     g1:33006.19/3.94MB     cms:33721.87/4.03MB  cms略胜一筹
4g     g1:30287.58/3.62MB     cms:43846.02/5.23MB cms略胜一筹

wrk -t 4 -c 40 -d30s http://localhost:8088/api/hello

4 threads and 40 connections 30s

1g     g1:28821.84/3.44MB     cms:36913.35/4.41MB ps:26955.17/3.22MB g1略胜一筹
4g     g1:36151.39/4.32MB     cms:30439.31/3.63MB g1略胜一筹
```


### 作业2

使用httpClient或者okHttp调用nio server

#### 

见 HttpClient01.java