package io.kimmking.rpcfx.demo.nettyserver.config;

import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.UserService;
import io.kimmking.rpcfx.demo.nettyserver.service.impl.OrderServiceImpl;
import io.kimmking.rpcfx.demo.nettyserver.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置接口的实现类
 *
 * @author lw
 */
@Configuration
public class BeanConfig {

    //io.kimmking.rpcfx.demo.nettyserver.service.impl
    @Bean("io.kimmking.rpcfx.demo.api.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    //io.kimmking.rpcfx.demo.api.OrderService
    @Bean("io.kimmking.rpcfx.demo.api.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
