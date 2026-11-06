package com.book.booklibrary.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Book {
    private Long id;
    // 图书名称
    private String bookName;
    // 作者
    private String author;
    // 出版社
    private String publisher;
    // 分类id（外键）
    private Long typeId;
    // 库存数量
    private Integer stock;
    // 封面图片地址
    private String coverImg;
    // 图书简介
    private String bookDesc;
    // 创建时间
    private Date createTime;
}