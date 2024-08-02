package net.lavaguides.student_management_system_2.controller;
import net.lavaguides.student_management_system_2.entity.AuthenticationResponse;
import net.lavaguides.student_management_system_2.entity.Users;
import net.lavaguides.student_management_system_2.service.AuthenticationService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    public AuthenticationController(AuthenticationService authservice) {
        this.authservice = authservice;
    }

    private final AuthenticationService authservice;

    @PostMapping("/S-M-S/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Users request) {
        return ResponseEntity.ok(authservice.register(request));
    }

    @PostMapping("/S-M-S/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Users request) {
        return ResponseEntity.ok(authservice.authenticate(request));
    }


}
