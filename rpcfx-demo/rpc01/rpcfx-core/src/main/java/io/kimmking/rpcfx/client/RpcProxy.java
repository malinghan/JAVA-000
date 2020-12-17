package io.kimmking.rpcfx.client;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description: rpc的缓存池
 * @date Date : 2020年12月18日 4:37 AM
 **/
public class RpcProxy {
    
    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();
    
    Object getProxy(String className) {
        return proxyCache.get(className);
    }
    
    Boolean isExit(String className) {
        return proxyCache.containsKey(className);
    }
    
    void add(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }
}
