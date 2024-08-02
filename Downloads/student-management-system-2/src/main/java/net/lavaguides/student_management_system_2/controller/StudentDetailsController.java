package net.lavaguides.student_management_system_2.controller;

import net.lavaguides.student_management_system_2.dto.StudentDetailsDto;
import net.lavaguides.student_management_system_2.service.StudentDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/S-M-S/studentDetails")
public class StudentDetailsController {

    private final StudentDetailsService studentDetailsService;

    public StudentDetailsController(StudentDetailsService studentDetailsService) {
        this.studentDetailsService = studentDetailsService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create/")
    public ResponseEntity<StudentDetailsDto> createStudentDetails(@RequestBody StudentDetailsDto studentDetailsDto) {
        StudentDetailsDto studentDetails = studentDetailsService.createStudentDetails(studentDetailsDto);
        return new ResponseEntity<>(studentDetails, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/getData/{id}")
    public ResponseEntity<StudentDetailsDto> getStudentDetails(@PathVariable("id") Long studentDetailsId) {
        StudentDetailsDto studentDetails = studentDetailsService.getStudentDetailsById(studentDetailsId);
        return ResponseEntity.ok(studentDetails);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/allData/")
    public ResponseEntity<List<StudentDetailsDto>> getAllStudentDetails() {
        List<StudentDetailsDto> studentDetails = studentDetailsService.getAllStudentDetails();
        return ResponseEntity.ok(studentDetails);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDetailsDto> updateStudentDetails(@PathVariable("id") Long studentDetailsId, @RequestBody StudentDetailsDto studentDetailsDto) {
        StudentDetailsDto updateStudentDetails = studentDetailsService.updateStudentDetails(studentDetailsId, studentDetailsDto);
        return ResponseEntity.ok(updateStudentDetails);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentDetails(@PathVariable("id") Long studentDetailsId) {
        studentDetailsService.deleteStudentDetails(studentDetailsId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
