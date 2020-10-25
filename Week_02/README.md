## 学习笔记


### 作业1

写一段对于不同GC的总结

#### 回答

##### 串行化GC
启动参数
```$xslt
java -XX:+UseSerialGC  -Xmx512m  -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps

-XX:+UseSerialGC 配置串行GC
-XX:+UserParNewGC 改进版本的Serial GC 可以配合CMS使用
```
在内存较小时，会发生很多fullgc,同时服务整体不可用。
原因是：串行化gc期间，会完全占用业务线程资源，用于垃圾回收

- 对年轻代使用mark-copy算法，对老年代使用mark-sweep-compact算法
- SerialGC、ParNewGC 都是单线程的垃圾收集器，不能进行并行处理。
- 在进行gc时，都会触发stw，停止所有应用线程
- CPU利用率高，暂停时间长。简单粗暴。


##### 并行gc
jdk8的默认gc策略,在不进行垃圾回收时，业务线程完全不收垃圾回收器的影响，所以能做到吞吐量优先

相关参数
```
• -XX:+UseParallelGC
• -XX:+UseParallelOldGC
• -XX:+UseParallelGC  -XX:+UseParallelOldGC
• -XX:ParallelGCThreads=N 指定GC线程数，默认为CPU核心数
```
特点
• 年轻代和老年代的垃圾回收都会触发STW
• 年轻代使用mark-copy ; 老年代使用mark-sweep-compact
• 并行垃圾收集器适用于多核服务器，目标是增加吞吐量。
• 在GC期间，所有CPU内核都在并行清理垃圾，总暂停时间更短；
• 在两次GC周期间隔，没有GC线程在运行，不会消耗任何系统资源；

##### cms
• 对年轻代采用并行SWT方式的mark-copy算法,对老年代使用并发mark-sweep算法
• CMS的设计目标是避免在老年代垃圾收集时出现长时间的停顿，实现手段如下
•   1. 不对老年代进行整理，而是使用free-list来管理空闲内存
• 2. 在mark-sweep阶段大部分工作是和应用线程一起工作的
• CMS默认的并发线程数是`CPU核心数的1/4` (`所以在内存较低的情况下不如ps gc`)
• 进行老年代的并发回收时，可能会伴随`多次的年轻代的MinorGC`

```
-XX:+UseConcMarkSweepGC
```

##### G1 GC
• Garbage-First 垃圾优先
• 让STW的时间和分布，变成可预测和可配置
• 通过设置某些特定的性能指标，达到该指标可以触发GC，或者控制GC过程
• 堆不再分成年轻代和老年代，而是划分成不同的regions, 每个小块可能一会是Eden区，一会是Old区
• G1 优先回收垃圾比较多的region

核心启动参数
```
• -XX:+UseG1GC
• -XX:MaxGCPauseMills=50
```
      

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

1g     g1:28821.84/3.44MB     cms:36913.35/4.41MB ps:26955.17/3.22MB cms略胜一筹
4g     g1:36151.39/4.32MB     cms:30439.31/3.63MB g1略胜一筹
```


### 作业2

使用httpClient或者okHttp调用nio server

#### 回答

见 HttpClient01.java

```java
public class HttpClient01 {

    public static void main(String[] args) throws Exception{
       String url = "http://localhost:8801";
        System.out.println(OkHttpHelper.doGet(url));
//        doGet(url);

    }

    public static void doGet(String url) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
                System.out.println("内容："+content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();
        }
    }

}
```