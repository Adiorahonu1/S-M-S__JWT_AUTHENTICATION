package net.lavaguides.student_management_system_2.service.Impl;

import net.lavaguides.student_management_system_2.Mapper.StudentDetailsMapper;
import net.lavaguides.student_management_system_2.dto.StudentDetailsDto;
import net.lavaguides.student_management_system_2.entity.StudentDetails;
import net.lavaguides.student_management_system_2.repository.StudentDetailsRepository;
import net.lavaguides.student_management_system_2.service.StudentDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {


    public StudentDetailsServiceImpl(StudentDetailsRepository studentDetailsRepository) {
        this.studentDetailsRepository = studentDetailsRepository;
    }

    private StudentDetailsRepository studentDetailsRepository;
    @Override
    public StudentDetailsDto createStudentDetails(StudentDetailsDto studentDetailsDto) {
        StudentDetails studentDetails = StudentDetailsMapper.mapToStudentDetails(studentDetailsDto);
        StudentDetails savedStudentDetails = studentDetailsRepository.save(studentDetails);

        return StudentDetailsMapper.mapToStudentDetailsDto(savedStudentDetails);
    }

    @Override
    public StudentDetailsDto getStudentDetailsById(Long studentDetailsId) {
        StudentDetails studentDetails = studentDetailsRepository.findById(studentDetailsId)
                .orElseThrow(()-> new RuntimeException("Id does not exist" + studentDetailsId));

        return StudentDetailsMapper.mapToStudentDetailsDto(studentDetails);
    }

    @Override
    public List<StudentDetailsDto> getAllStudentDetails() {
        List<StudentDetails> studentDetail = studentDetailsRepository.findAll();
        return studentDetail.stream()
                .map((studentDetails)-> StudentDetailsMapper
                        .mapToStudentDetailsDto(studentDetails))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDetailsDto updateStudentDetails(Long studentDetailsId, StudentDetailsDto studentDetailsDto) {
        StudentDetails studentDetails = studentDetailsRepository.findById(studentDetailsId)
                .orElseThrow(()-> new RuntimeException("could not find Id:" + studentDetailsId));

        studentDetails.setStudentFirstName(studentDetailsDto.StudentFirstName());
        studentDetails.setStudentLastName(studentDetailsDto.StudentLastName());
        studentDetails.setEmail(studentDetailsDto.email());

        StudentDetails updateStudentDetails = studentDetailsRepository.save(studentDetails);
        return StudentDetailsMapper.mapToStudentDetailsDto(updateStudentDetails);

    }

    @Override
    public void deleteStudentDetails(Long studentDetailsId) {
        StudentDetails studentDetails = studentDetailsRepository.findById(studentDetailsId)
                .orElseThrow(()-> new RuntimeException("this ID does not exist:" + studentDetailsId));
        studentDetailsRepository.delete(studentDetails);

    }


}
