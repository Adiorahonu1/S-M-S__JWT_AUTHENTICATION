package net.lavaguides.student_management_system_2.repository;

import net.lavaguides.student_management_system_2.entity.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, Long> {
}
