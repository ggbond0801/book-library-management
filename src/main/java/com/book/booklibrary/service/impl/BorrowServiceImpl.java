package com.book.booklibrary.service.impl;

import com.book.booklibrary.entity.Borrow;
import com.book.booklibrary.mapper.BookMapper;
import com.book.booklibrary.mapper.BorrowMapper;
import com.book.booklibrary.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public BigDecimal calcFine(Borrow borrow) {
        // 已经归还，不罚款
        if (borrow.getStatus() == 1) {
            return BigDecimal.ZERO;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = borrow.getDeadlineTime();
        // 当前时间没超过到期时间 → 不罚款
        if (now.isBefore(deadline)) {
            return BigDecimal.ZERO;
        }
        // 计算逾期多少天
        long days = ChronoUnit.DAYS.between(deadline.toLocalDate(), now.toLocalDate());
        // 每日罚金0.5元
        BigDecimal perDay = new BigDecimal("0.5");
        return perDay.multiply(new BigDecimal(days));
    }

    @Override
    @Transactional
    public int addBorrow(Borrow borrow) {
        int insertRow = borrowMapper.insert(borrow);
        bookMapper.updateStock(borrow.getBookId(), -1);
        return insertRow;
    }

    @Override
    public List<Borrow> getMyBorrow(Long userId) {
        return borrowMapper.selectByUserId(userId);
    }

    @Override
    public List<Borrow> getNoReturnBorrow(Long userId) {
        return borrowMapper.selectNoReturnByUserId(userId);
    }

    /**
     * 还书：自动计算逾期罚款、更新归还时间与罚金、库存+1
     */
    @Override
    @Transactional
    public boolean returnBook(Long borrowId, Long bookId) {
        Borrow borrow = borrowMapper.selectById(borrowId);
        if (borrow == null) {
            return false;
        }
        BigDecimal fineMoney = calcFine(borrow);
        LocalDateTime returnTime = LocalDateTime.now();
        int row1 = borrowMapper.returnBook(borrowId, returnTime, fineMoney);
        int row2 = bookMapper.updateStock(bookId, 1);
        return row1 > 0 && row2 > 0;
    }
}
