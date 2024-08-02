package net.lavaguides.student_management_system_2.service;

import net.lavaguides.student_management_system_2.dto.StudentAccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentAccountService {

    StudentAccountDto createStudentAccount(StudentAccountDto studentAccountDto);

    StudentAccountDto getStudentAccount(Long studentAccountId);

    List<StudentAccountDto> getAllStudentAccount();

    StudentAccountDto updateStudentAccount(Long studentAccountId, StudentAccountDto studentAccountDto);

    void deleteStudentAccount(Long studentAccountId);


}
