package io.kimmking.rpcfx.api;

import java.util.List;

/**
 * 负载均衡器
 */
public interface LoadBalancer {
    
    String select(List<String> urls);
    
}
