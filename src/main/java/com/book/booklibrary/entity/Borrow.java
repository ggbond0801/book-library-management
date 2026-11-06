package com.book.booklibrary.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Borrow {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private LocalDateTime deadlineTime;
    private BigDecimal fine;
    private Integer status;
}
