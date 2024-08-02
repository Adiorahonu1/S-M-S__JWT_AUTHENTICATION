package net.lavaguides.student_management_system_2.Mapper;

import net.lavaguides.student_management_system_2.dto.StudentAccountDto;
import net.lavaguides.student_management_system_2.dto.StudentDetailsDto;
import net.lavaguides.student_management_system_2.entity.StudentAccount;
import net.lavaguides.student_management_system_2.entity.StudentDetails;

public class StudentAccountMapper {
    public static StudentAccount mapToStudentAccount(StudentAccountDto studentAccountDto){
        StudentDetails studentDetails = null;
        if(studentAccountDto.studentDetailsDto() != null){
            studentDetails = new StudentDetails();
            studentDetails.setId(studentAccountDto.studentDetailsDto().id());
        }


        return new StudentAccount(
                studentAccountDto.id(),
                studentAccountDto.amount(),
                studentAccountDto.accountStatus(),
                studentAccountDto.paidDate(),
                studentDetails
        );


    }


    public static StudentAccountDto mapToStudentAccountDto(StudentAccount studentAccount){
        StudentDetailsDto studentDetailsDto = null;
        if(studentAccount.getStudentDetails() != null){
            studentDetailsDto = new StudentDetailsDto(
                    studentAccount.getStudentDetails().getId(),
                    studentAccount.getStudentDetails().getStudentFirstName(),
                    studentAccount.getStudentDetails().getStudentLastName(),
                    studentAccount.getStudentDetails().getEmail()
            );
        }


        return  new StudentAccountDto(
                studentAccount.getId(),
                studentAccount.getAmount(),
                studentAccount.getAccountStatus(),
                studentAccount.getPaidDate(),
                studentDetailsDto
        );


    }
}
