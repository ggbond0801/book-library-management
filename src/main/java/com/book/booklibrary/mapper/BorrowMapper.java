package com.book.booklibrary.mapper;

import com.book.booklibrary.entity.Borrow;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BorrowMapper {
    int insert(Borrow borrow);

    Borrow selectById(Long id);

    List<Borrow> selectByUserId(Long userId);

    List<Borrow> selectNoReturnByUserId(Long userId);

    int returnBook(@Param("borrowId") Long borrowId,
                   @Param("returnTime") java.time.LocalDateTime returnTime,
                   @Param("fine") java.math.BigDecimal fine);
}
