package com.book.booklibrary.controller;

import com.book.booklibrary.entity.Borrow;
import com.book.booklibrary.service.BorrowService;
import com.book.booklibrary.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    // 借书
    @PostMapping("/add")
    public Result<String> add(@RequestBody Borrow borrow) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = now.plus(15, ChronoUnit.DAYS);

        borrow.setBorrowTime(now);
        borrow.setDeadlineTime(deadline);
        borrow.setStatus(0);
        borrow.setFine(BigDecimal.ZERO);
        borrow.setReturnTime(null);

        borrowService.addBorrow(borrow);
        return Result.success("借书成功");
    }

    // 查询本人所有借阅
    @GetMapping("/my")
    public Result<List<Borrow>> myList(@RequestParam Long userId) {
        List<Borrow> list = borrowService.getMyBorrow(userId);
        return Result.success(list);
    }

    // 还书接口
    @PostMapping("/return")
    public Result<String> returnBook(@RequestParam Long borrowId, @RequestParam Long bookId) {
        boolean ok = borrowService.returnBook(borrowId, bookId);
        if (ok) {
            return Result.success("还书成功，库存已恢复");
        } else {
            return Result.error("还书失败");
        }
    }
}
