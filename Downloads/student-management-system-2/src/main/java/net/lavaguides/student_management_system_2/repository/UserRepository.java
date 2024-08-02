package net.lavaguides.student_management_system_2.repository;

import net.lavaguides.student_management_system_2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {


    Optional<Users> findByUsername(String username);
}
