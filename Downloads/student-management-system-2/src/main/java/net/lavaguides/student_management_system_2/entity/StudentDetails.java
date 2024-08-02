package net.lavaguides.student_management_system_2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studentDetails")
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String StudentFirstName;
    @Column(nullable = false)
    private String StudentLastName;
    @Column(nullable = false,unique = true)
    private String email;
}
