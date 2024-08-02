package net.lavaguides.student_management_system_2.repository;

import net.lavaguides.student_management_system_2.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {
}
