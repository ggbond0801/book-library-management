package com.book.booklibrary.service;

import com.book.booklibrary.entity.Borrow;

import java.math.BigDecimal;
import java.util.List;

public interface BorrowService {
    // 添加借阅（借书）
    int addBorrow(Borrow borrow);
    // 查询我的全部借阅记录
    List<Borrow> getMyBorrow(Long userId);
    // 查询我未归还的图书
    List<Borrow> getNoReturnBorrow(Long userId);
    // 还书业务方法
    boolean returnBook(Long borrowId, Long bookId);
    // 计算逾期罚款
    BigDecimal calcFine(Borrow borrow);

}
