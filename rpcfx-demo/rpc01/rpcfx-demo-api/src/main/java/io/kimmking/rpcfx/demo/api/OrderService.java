package io.kimmking.rpcfx.demo.api;

public interface OrderService {
    
    Order findOrderById(Integer id);
    
    Order findError();
    
}
