package com.book.booklibrary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描mapper接口包，替换成你自己的mapper包路径
@MapperScan("com.book.booklibrary.mapper")
public class BookLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }
}