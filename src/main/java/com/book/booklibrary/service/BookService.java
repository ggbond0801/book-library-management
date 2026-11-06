package com.book.booklibrary.service;

import com.book.booklibrary.entity.Book;
import com.book.booklibrary.util.PageResult;

import java.util.List;

public interface BookService {
    List<Book> getBookList(String bookName);
    Book getBookById(Long id);
    void addBook(Book book);
    void editBook(Book book);
    void removeBook(Long id);
    void changeStock(Long id, Integer num);
    PageResult<Book> getBookPage(String bookName, Integer pageNum, Integer pageSize);

}