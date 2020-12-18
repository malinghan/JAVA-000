package io.kimmking.rpcfx.demo.nettyserver.service.impl;


import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

/**
 * @author lw
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "RPC");
    }
}
