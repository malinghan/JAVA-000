package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.transport.RpcNettyTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description:
 * @date Date : 2020年12月18日 3:24 AM
 **/
public class RpcInvocationJDKHandler implements InvocationHandler {
    
    private final Class<?> serviceClass;
    private final String url;
    
    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }
    
    <T> RpcInvocationJDKHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass; this.url = url; ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
    
    
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) {
        //使用netty+http
        return new RpcNettyTransport().process(serviceClass, method, args, url);
    }
}
