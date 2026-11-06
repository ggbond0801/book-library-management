package com.book.booklibrary.mapper;

import com.book.booklibrary.entity.Book;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BookMapper {
    // 分页查询所有图书，支持名称模糊搜索
    List<Book> selectAll(@Param("bookName") String bookName);

    // 根据id查询单本图书
    Book selectById(Long id);

    // 新增图书
    int insert(Book book);

    // 修改图书信息
    int update(Book book);

    // 删除图书
    int deleteById(Long id);

    // 修改库存（借书/还书专用）
    int updateStock(@Param("id") Long id, @Param("num") Integer num);
    // ==========下面新加分页2个方法==========
    /**
     * 分页模糊查询图书
     * @param bookName 搜索名称
     * @param offset 偏移量 (pageNum-1)*pageSize
     * @param pageSize 每页条数
     */
    List<Book> selectPage(@Param("bookName") String bookName,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize);

    /**
     * 统计符合条件的图书总条数
     */
    Long count(@Param("bookName") String bookName);
}