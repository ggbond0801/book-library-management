package com.book.booklibrary.controller;

import com.book.booklibrary.entity.Book;
import com.book.booklibrary.service.BookService;
import com.book.booklibrary.util.PageResult;
import com.book.booklibrary.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.alibaba.excel.EasyExcel;
import com.book.booklibrary.entity.BookExcel;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    // 模糊查询图书列表（旧接口，保留）
    @GetMapping("/list")
    public Result<List<Book>> list(@RequestParam(required = false) String bookName) {
        List<Book> list = bookService.getBookList(bookName);
        return Result.success(list);
    }

    // 根据id查询单本图书
    @GetMapping("/{id}")
    public Result<Book> getOne(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return Result.success(book);
    }

    // 新增图书
    @PostMapping("/add")
    public Result<String> add(@RequestBody Book book) {
        bookService.addBook(book);
        return Result.success("新增成功");
    }

    // 修改图书
    @PutMapping("/update")
    public Result<String> update(@RequestBody Book book) {
        bookService.editBook(book);
        return Result.success("修改成功");
    }

    // 删除图书
    @DeleteMapping("/remove/{id}")
    public Result<String> delete(@PathVariable Long id) {
        bookService.removeBook(id);
        return Result.success("删除成功");
    }

    // 修改库存（借书传-1，还书传+1）
    @PostMapping("/stock")
    public Result<String> stock(@RequestParam Long id, @RequestParam Integer num) {
        bookService.changeStock(id, num);
        return Result.success("库存修改成功");
    }

    // ============【新加的分页接口】============
    @GetMapping("/page")
    public Result<PageResult<Book>> page(
            @RequestParam(required = false) String bookName,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "8") Integer pageSize
    ) {
        PageResult<Book> pageData = bookService.getBookPage(bookName, pageNum, pageSize);
        return Result.success(pageData);
    }
    // Excel导出全部/搜索后的图书
    @GetMapping("/export")
    public void export(
            @RequestParam(required = false) String bookName,
            HttpServletResponse response
    ) throws Exception {
        // 查询数据
        List<Book> bookList = bookService.getBookList(bookName);

        // Book实体 → BookExcel导出对象转换
        List<BookExcel> excelDataList = bookList.stream().map(book -> {
            BookExcel excel = new BookExcel();
            excel.setId(book.getId());
            excel.setBookName(book.getBookName());
            excel.setAuthor(book.getAuthor());
            excel.setPublisher(book.getPublisher());
            excel.setStock(book.getStock());
            excel.setBookDesc(book.getBookDesc());
            return excel;
        }).toList();

        // 设置响应头，浏览器自动下载文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("图书信息表", "UTF-8");
        response.setHeader("Content‑Disposition", "attachment;filename=" + fileName + ".xlsx");

        // 使用EasyExcel写入输出流
        EasyExcel.write(response.getOutputStream(), BookExcel.class)
                .sheet("图书列表")
                .doWrite(excelDataList);
    }

}
