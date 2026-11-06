package com.book.booklibrary.service.impl;

import com.book.booklibrary.entity.User;
import com.book.booklibrary.mapper.UserMapper;
import com.book.booklibrary.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
