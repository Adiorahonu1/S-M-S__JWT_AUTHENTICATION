package net.lavaguides.student_management_system_2.controller;

import net.lavaguides.student_management_system_2.dto.StudentAccountDto;
import net.lavaguides.student_management_system_2.service.StudentAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/S-M-S/StudentAccount")
public class StudentAccountController {

    private final StudentAccountService studentAccountService;

    public StudentAccountController(StudentAccountService studentAccountService) {
        this.studentAccountService = studentAccountService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StudentAccountDto> createStudentAccount(@RequestBody StudentAccountDto studentAccountDto){
        StudentAccountDto studentAccount = studentAccountService.createStudentAccount(studentAccountDto);
        return new ResponseEntity<>(studentAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<StudentAccountDto> getStudentAccount(@PathVariable("id") Long studentAccountId){
        StudentAccountDto studentAccount = studentAccountService.getStudentAccount(studentAccountId);
        return ResponseEntity.ok(studentAccount);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<StudentAccountDto>> getAllStudentAccount(){
        List<StudentAccountDto> studentAccount = studentAccountService.getAllStudentAccount();
        return ResponseEntity.ok(studentAccount);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StudentAccountDto> updateStudentAccount(@PathVariable("id") Long studentAccountId,
                                                                  @RequestBody StudentAccountDto studentAccountDto) {
        StudentAccountDto studentAccount = studentAccountService
                .updateStudentAccount(studentAccountId, studentAccountDto);
        return ResponseEntity.ok(studentAccount);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteStudentAccount(@PathVariable("id") Long studentAccountId){
        studentAccountService.deleteStudentAccount(studentAccountId);
        return ResponseEntity.ok("Deleted successfully");
    }
}

