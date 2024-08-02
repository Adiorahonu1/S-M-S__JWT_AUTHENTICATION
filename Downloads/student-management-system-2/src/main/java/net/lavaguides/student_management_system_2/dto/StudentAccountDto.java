package net.lavaguides.student_management_system_2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StudentAccountDto (Long id,
                                 BigDecimal amount,
                                 String accountStatus,
                                 LocalDate paidDate,
                                 StudentDetailsDto studentDetailsDto) {
}
