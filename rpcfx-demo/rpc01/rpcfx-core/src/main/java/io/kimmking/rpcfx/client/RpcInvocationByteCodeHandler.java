package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.parser.ParserConfig;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description:
 * @date Date : 2020年12月18日 4:31 AM
 **/
public class RpcInvocationByteCodeHandler implements MethodInterceptor {
    
    //?
    private final Class<?> serviceClass;
    private final String url;
    
    /**
     * @param serviceClass
     * @param url
     * @param <T>
     */
    <T> RpcInvocationByteCodeHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
    
    @Override
    public Object intercept(final Object o, final Method method, final Object[] args, final MethodProxy methodProxy){
        return  new RpcNettyTransport().process(serviceClass, method, args, url);
    }
}
