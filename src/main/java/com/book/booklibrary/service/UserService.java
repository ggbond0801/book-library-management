package com.book.booklibrary.service;

import com.book.booklibrary.entity.User;

public interface UserService {
    User login(String username, String password);
}
