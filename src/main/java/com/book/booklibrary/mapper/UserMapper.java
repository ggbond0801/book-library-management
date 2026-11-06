package com.book.booklibrary.mapper;

import com.book.booklibrary.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    // 根据账号密码查询用户，登录用
    User login(@Param("username") String username, @Param("password") String password);
}
