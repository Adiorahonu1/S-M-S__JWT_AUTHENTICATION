package net.lavaguides.student_management_system_2.repository;

import net.lavaguides.student_management_system_2.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
        select t from Token t inner join Users  u
        on t.user.id = u.id
        where t.user.id = :userId and t.loggedout = false
""")
    List<Token> findAllTokenByUser(Long userId);

    Optional<Token> findByToken (String token);

}
