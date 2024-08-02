package net.lavaguides.student_management_system_2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studentAccount")
public class StudentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    @Column(nullable = false)
    private String accountStatus;
    @Column(nullable = false)
    private LocalDate paidDate;


    @ManyToOne(optional = false)
    @JoinColumn (name = "studentDetails_id",nullable = false)
    private StudentDetails studentDetails;
}
