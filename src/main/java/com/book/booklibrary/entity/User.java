package com.book.booklibrary.entity;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String role; // admin 管理员 / reader 读者
}
