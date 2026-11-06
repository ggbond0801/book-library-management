package com.book.booklibrary.controller;

import com.book.booklibrary.entity.User;
import com.book.booklibrary.service.UserService;
import com.book.booklibrary.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@RequestBody User loginUser){
        User user = userService.login(loginUser.getUsername(), loginUser.getPassword());
        if(user == null){
            // 使用 error，匹配你的工具类
            return Result.error("账号或密码错误");
        }
        // 查询成功，返回完整用户对象（携带role角色）
        return Result.success(user);
    }
}
