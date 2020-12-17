package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.LoadBalancer;
import io.kimmking.rpcfx.api.Router;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description:
 * @date Date : 2020年12月18日 4:42 AM
 **/
public class RpcClientByteBuddy implements RpcClient {
    
    @Override
    //Lombok注解-@SneakyThrows: https://www.jianshu.com/p/7d0ed3aef34b
    @SneakyThrows()
    public <T> T create(final Class<T> serviceClass, final String url, final Filter... filters) {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationJDKHandler(serviceClass, url)))
                .make()
                .load(RpcClientByteBuddy.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
    
    @Override
    public <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, final Router router, final LoadBalancer loadBalance, final Filter filter) {
        return null;
    }
}
