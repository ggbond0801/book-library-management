package com.book.booklibrary.service.impl;

import com.book.booklibrary.entity.Book;
import com.book.booklibrary.mapper.BookMapper;
import com.book.booklibrary.service.BookService;
import com.book.booklibrary.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Book> getBookList(String bookName) {
        return bookMapper.selectAll(bookName);
    }

    @Override
    public Book getBookById(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public void editBook(Book book) {
        bookMapper.update(book);
    }

    @Override
    public void removeBook(Long id) {
        bookMapper.deleteById(id);
    }

    @Override
    public void changeStock(Long id, Integer num) {
        bookMapper.updateStock(id, num);
    }
    @Override
    public PageResult<Book> getBookPage(String bookName, Integer pageNum, Integer pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;
        List<Book> dataList = bookMapper.selectPage(bookName, offset, pageSize);
        Long totalCount = bookMapper.count(bookName);

        PageResult<Book> page = new PageResult<>();
        page.setData(dataList);
        page.setTotal(totalCount);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return page;
    }

}