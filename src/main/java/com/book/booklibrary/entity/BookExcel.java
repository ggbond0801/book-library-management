package com.book.booklibrary.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ColumnWidth(20)
@ContentRowHeight(20)
@HeadRowHeight(25)
public class BookExcel {

    @ExcelProperty("图书ID")
    private Long id;

    @ExcelProperty("图书名称")
    private String bookName;

    @ExcelProperty("作者")
    private String author;

    @ExcelProperty("出版社")
    private String publisher;

    @ExcelProperty("库存数量")
    private Integer stock;

    @ExcelProperty("图书简介")
    private String bookDesc;
}
