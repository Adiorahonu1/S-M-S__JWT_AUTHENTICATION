package net.lavaguides.student_management_system_2.service;

import net.lavaguides.student_management_system_2.dto.StudentDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StudentDetailsService {

    StudentDetailsDto createStudentDetails(StudentDetailsDto studentDetailsDto);


    StudentDetailsDto getStudentDetailsById(Long studentDetailsId);

    List<StudentDetailsDto> getAllStudentDetails();

    StudentDetailsDto updateStudentDetails(Long studentDetailsId, StudentDetailsDto studentDetailsDto);

    void deleteStudentDetails(Long studentDetailsId);

}
