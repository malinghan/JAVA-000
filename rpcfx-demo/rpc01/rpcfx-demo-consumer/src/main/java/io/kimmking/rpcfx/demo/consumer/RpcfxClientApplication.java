package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.client.RpcClient;
import io.kimmking.rpcfx.client.RpcClientByteBuddy;
import io.kimmking.rpcfx.client.RpcClientCglib;
import io.kimmking.rpcfx.client.RpcClientJdk;
import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RpcfxClientApplication {
    
    // 二方库
    // 三方库 lib
    // nexus, userserivce -> userdao -> user
    //
    
    public static void main(String[] args) {
        
        // UserService service = new xxx();
        // service.findById
        
//        UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
//        User user = userService.findById(1);
//        System.out.println("find user id=1 from server: " + user.getName());
    
    
        invokeByCglib();
        System.out.println("=======================================");
        invokeByByteBuddy();
        System.out.println("=======================================");
        invokeByJDK();
      
        //
        //		UserService userService2 = Rpcfx.createFromRegistry(UserService.class, "localhost:2181", new TagRouter(), new RandomLoadBalancer(), new CuicuiFilter());
        
        //		SpringApplication.run(RpcfxClientApplication.class, args);
    }
    
    private static void invokeByCglib(){
        RpcClient clientCglib = new RpcClientCglib();
        OrderService orderService = clientCglib.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1992129);
        if (order == null) {
            log.info("Clint service invoke Error"); return;
        }
        System.out.println(String.format("find order name=%s, user=%d", order.getName(), order.getId()));
    }
    
    private static void invokeByByteBuddy(){
        RpcClient clientByteBuddy = new RpcClientByteBuddy();
        OrderService orderService = clientByteBuddy.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1992129);
        if (order == null) {
            log.info("Clint service invoke Error"); return;
        }
        System.out.println(String.format("find order name=%s, user=%d", order.getName(), order.getId()));
    }
    
    private static void invokeByJDK(){
        RpcClient clientByteBuddy = new RpcClientJdk();
        OrderService orderService = clientByteBuddy.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1992129);
        if (order == null) {
            log.info("Clint service invoke Error"); return;
        }
        System.out.println(String.format("find order name=%s, user=%d", order.getName(), order.getId()));
    }
    
}



