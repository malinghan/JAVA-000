package io.kimmking.rpcfx.demo.nettyserver.service.impl;


import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.exception.CustomException;

/**
 * @author lw
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(Integer id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom exception");
    }
}
