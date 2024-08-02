package net.lavaguides.student_management_system_2.service.Impl;

import net.lavaguides.student_management_system_2.Mapper.StudentAccountMapper;
import net.lavaguides.student_management_system_2.Mapper.StudentDetailsMapper;
import net.lavaguides.student_management_system_2.dto.StudentAccountDto;
import net.lavaguides.student_management_system_2.entity.StudentAccount;
import net.lavaguides.student_management_system_2.entity.StudentDetails;
import net.lavaguides.student_management_system_2.repository.StudentAccountRepository;
import net.lavaguides.student_management_system_2.repository.StudentDetailsRepository;
import net.lavaguides.student_management_system_2.service.StudentAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentAccountServiceImpl implements StudentAccountService {



    private StudentAccountRepository studentAccountRepository;

    public StudentAccountServiceImpl(StudentAccountRepository studentAccountRepository, StudentDetailsRepository studentDetailsRepository) {
        this.studentAccountRepository = studentAccountRepository;
        this.studentDetailsRepository = studentDetailsRepository;
    }

    private StudentDetailsRepository studentDetailsRepository;
    @Override
    public StudentAccountDto createStudentAccount(StudentAccountDto studentAccountDto) {
        StudentAccount studentAccount = StudentAccountMapper.mapToStudentAccount(studentAccountDto);
        StudentAccount savedAccount = studentAccountRepository.save(studentAccount);
        return StudentAccountMapper.mapToStudentAccountDto(savedAccount);
    }

    @Override
    public StudentAccountDto getStudentAccount(Long studentAccountId) {
        StudentAccount studentAccount = studentAccountRepository.findById(studentAccountId)
                .orElseThrow(()-> new RuntimeException("Could not find this is Id: " + studentAccountId));

        return StudentAccountMapper.mapToStudentAccountDto(studentAccount);
    }

    @Override
    public List<StudentAccountDto> getAllStudentAccount() {
        List<StudentAccount> studentAccounts = studentAccountRepository.findAll();

        return studentAccounts.stream().map((studentAccount)-> StudentAccountMapper
                .mapToStudentAccountDto(studentAccount)).collect(Collectors.toList());
    }

    @Override
    public StudentAccountDto updateStudentAccount(Long studentAccountId, StudentAccountDto studentAccountDto) {
        StudentAccount studentAccount = studentAccountRepository.findById(studentAccountId)
                .orElseThrow(()-> new RuntimeException("couldnt find this Id:" + studentAccountId));

        if(studentAccountDto.studentDetailsDto() != null){
            StudentDetails studentDetails = studentDetailsRepository
                    .findById(studentAccountDto.studentDetailsDto().id())
                    .orElseThrow(()-> new RuntimeException("this id could not be found: " + studentAccountDto.studentDetailsDto().id()));
            studentAccount.setStudentDetails(studentDetails);
        }

        studentAccount.setAmount(studentAccountDto.amount());
        studentAccount.setAccountStatus(studentAccountDto.accountStatus());
        studentAccount.setPaidDate(studentAccountDto.paidDate());
        StudentAccount updatedStudentAccount = studentAccountRepository.save(studentAccount);

        return StudentAccountMapper.mapToStudentAccountDto(updatedStudentAccount);
    }

    @Override
    public void deleteStudentAccount(Long studentAccountId) {

        StudentAccount studentAccount = studentAccountRepository.findById(studentAccountId)
                .orElseThrow(()-> new RuntimeException("this id could not be found: " + studentAccountId));
        studentAccountRepository.delete(studentAccount);
    }
}
