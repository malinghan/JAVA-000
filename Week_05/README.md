## 第9课
### 基础题
#### 作业要求
1.（选做）使用Java中的动态代理，实现一个简单的AOP
#### 实现说明

实现代码: https://github.com/malinghan/interview/tree/master/springs/src/main/java/com/someecho/spring03

```java
public class AopJDKDynamicProxyFactory implements InvocationHandler {

     private Object target;

     public Object bind(Object target){
         this.target = target;
         return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
     }

     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
         Annotation[] annotations = method.getAnnotations();
         System.out.println(annotations);
         Transaction transactional = method.getAnnotation(Transaction.class);
         Log log = method.getAnnotation(Log.class);
         Object result = null;
         if(transactional != null){
             new TransactionAspect().before();
         }
         if(log != null){
             new LogAspect().before();
         }
         result = method.invoke(this.target,args);
         if(transactional != null){
             new TransactionAspect().after();
         }
         if(log != null){
             new LogAspect().after();
         }
         return  result;
     }
}
```



#### 作业要求
2. (必做) 写代码实现Spring Bean的装配，方式越多越好(XML、Annotation都可以)



#### 实现说明


#### 作业要求
3. (选做) 实现一个Spring XML自定义配置，配置一组Bean，例如Student/Klass/School


#### 实现说明
