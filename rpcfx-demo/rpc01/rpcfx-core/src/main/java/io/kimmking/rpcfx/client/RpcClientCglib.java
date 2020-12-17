package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.LoadBalancer;
import io.kimmking.rpcfx.api.Router;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description:
 * @date Date : 2020年12月18日 4:42 AM
 **/
@Slf4j
public class RpcClientCglib implements RpcClient {
    
    
    @Override
    public <T> T create(final Class<T> serviceClass, final String url, final Filter... filters) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new RpcInvocationByteCodeHandler(serviceClass, url));
        //父类为serviceClass
        enhancer.setSuperclass(serviceClass);
        log.info("client cglib proxy instance create and return");
        return (T) enhancer.create();
    }
    
    @Override
    public <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, final Router router, final LoadBalancer loadBalance, final Filter filter) {
        return null;
    }
}
