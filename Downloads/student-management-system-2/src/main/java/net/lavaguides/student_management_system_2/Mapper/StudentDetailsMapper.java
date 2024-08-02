package net.lavaguides.student_management_system_2.Mapper;

import net.lavaguides.student_management_system_2.dto.StudentDetailsDto;
import net.lavaguides.student_management_system_2.entity.StudentDetails;

public class StudentDetailsMapper {
    public static StudentDetailsDto mapToStudentDetailsDto(StudentDetails studentDetails){
        StudentDetailsDto studentDetailsDto = new StudentDetailsDto(
                studentDetails.getId(),
                studentDetails.getStudentFirstName(),
                studentDetails.getStudentLastName(),
                studentDetails.getEmail()
        );
                return studentDetailsDto;
    }


    public static StudentDetails mapToStudentDetails(StudentDetailsDto studentDetailsDto){
        StudentDetails studentDetails = new StudentDetails(
                studentDetailsDto.id(),
                studentDetailsDto.StudentFirstName(),
                studentDetailsDto.StudentLastName(),
                studentDetailsDto.email()
        );
        return studentDetails;
    }
}
