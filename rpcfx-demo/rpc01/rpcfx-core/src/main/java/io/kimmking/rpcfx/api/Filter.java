package io.kimmking.rpcfx.api;

/**
 * 过滤器
 */
public interface Filter {
    
    boolean filter(RpcfxRequest request);
    
    Filter next();
    
}
