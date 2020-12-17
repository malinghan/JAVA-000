package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.LoadBalancer;
import io.kimmking.rpcfx.api.Router;

import java.lang.reflect.Proxy;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description: 这里是客户端
 * @date Date : 2020年12月18日 3:20 AM
 **/
public class RpcClientJdk implements RpcClient{
    
    
    @Override
    public <T> T create(final Class<T> serviceClass, final String url, final Filter... filters) {
        ClassLoader loader = RpcClientJdk.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return (T) Proxy.newProxyInstance(loader, classes, new RpcInvocationJDKHandler(serviceClass, url));
    }
    
    @Override
    public <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, final Router router, final LoadBalancer loadBalance, final Filter filter) {
        return null;
    }
}
