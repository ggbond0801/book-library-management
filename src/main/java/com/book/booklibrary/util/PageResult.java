package com.book.booklibrary.util;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private List<T> data;
    private Long total;
    private Integer pageNum;
    private Integer pageSize;
}
