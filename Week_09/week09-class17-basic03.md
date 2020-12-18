## 题目要求
改造自定义的RPC程序，提交到github
1. 将服务端写死查找接口实现类变成泛型和反射
2. 将客户端动态代理改成字节码生成+AOP
3. 使用Netty+Http作为client传输方式


1. 见`RpcServerHandler.invoke`

```
  /**
     * 获取接口实现对应的bean，反射调用方法，返回结果
     *
     * @param request rpc request
     * @return result
     */
    private RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse(); String serviceClass = request.getServiceClass();
        
        // 作业1：改成泛型和反射
        Object service = applicationContext.getBean(serviceClass);
        
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams());
            log.info("Server method invoke result: " + result.toString());
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName)); response.setStatus(true);
            log.info("Server Response serialize to string return");
            return response;
        } catch (IllegalAccessException | InvocationTargetException | CustomException e) {
            e.printStackTrace(); response.setException(e); response.setStatus(false); return response;
        }
    }
```

2. 见 `io.kimmking.rpcfx.client.RpcClient`

- RpcClientByteBuddy  ByteBuddy的实现
- RpcClientByteCglib  Cglib的实现
- RpcClientJdk          Jdk动态代理的实现

3. 使用Netty+Http作为client传输方式 见: `RpcNettyTransport`

```java
public class RpcNettyTransport implements RpcTransport{
    
    private Logger log = LoggerFactory.getLogger(RpcNettyTransport.class);
    
    /**
     * 发送请求到服务端
     * 获取结果后序列号成对象，返回
     *
     * @param service service name
     * @param method service method
     * @param params method params
     * @param url server host
     * @return object
     */
    @Override
    public Object process(Class<?> service, Method method, Object[] params, String url) {
        log.info("Client proxy instance method invoke");
        
        // 自定义了Rpc请求的结构 RpcRequest,放入接口名称、方法名、参数
        log.info("Build Rpc request"); RpcfxRequest rpcRequest = new RpcfxRequest();
        rpcRequest.setServiceClass(service.getName()); 
        rpcRequest.setMethod(method.getName());
        rpcRequest.setParams(params);
        
        // 客户端使用的 netty，发送请求到服务端，拿到结果（自定义结构：rpcfxResponse)
        log.info("Client send request to Server"); RpcfxResponse rpcResponse; 
        try {
            //这里使用netty+http作为网络传输调用: RpcNettyClientSync,使用fastjson进行序列化
            rpcResponse = RpcNettyClientSync.getInstance().getResponse(rpcRequest, url);
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace(); return null;
        }
        
        log.info("Client receive response Object"); assert rpcResponse != null;
        if (!rpcResponse.getStatus()) {
            log.info("Client receive exception"); rpcResponse.getException().printStackTrace(); return null;
        }
        
        // 序列化成对象返回
        log.info("Response:: " + rpcResponse.getResult()); return JSON.parse(rpcResponse.getResult().toString());
    }
}
```

## 感谢

https://github.com/lw1243925457/JAVA-000