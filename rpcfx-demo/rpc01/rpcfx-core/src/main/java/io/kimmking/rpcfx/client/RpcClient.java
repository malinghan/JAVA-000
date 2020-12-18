package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.LoadBalancer;
import io.kimmking.rpcfx.api.Router;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.client
 * @Description:
 * @date Date : 2020年12月18日 3:17 AM
 **/
public interface RpcClient {
    
    /**
     * normal connect create
     *
     * @param serviceClass
     * @param url
     * @param filters
     * @param <T>
     * @return
     */
    <T> T create(final Class<T> serviceClass, final String url, Filter... filters);
    
    
    /**
     * zk register
     *
     * @param serviceClass
     * @param zkUrl
     * @param router
     * @param loadBalance
     * @param filter
     * @param <T>
     * @param <filters>
     * @return
     */
    <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter);
}
