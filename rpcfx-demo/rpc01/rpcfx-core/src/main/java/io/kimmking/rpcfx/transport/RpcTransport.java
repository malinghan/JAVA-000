package io.kimmking.rpcfx.transport;

import java.lang.reflect.Method;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.transport
 * @Description:
 * @date Date : 2020年12月18日 12:38 PM
 **/
public interface RpcTransport{
    
    Object process(Class<?> service, Method method, Object[] params, String url);
}
