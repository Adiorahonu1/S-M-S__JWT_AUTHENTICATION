package net.lavaguides.student_management_system_2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Setter
@Getter
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String token;
    private boolean loggedout;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
