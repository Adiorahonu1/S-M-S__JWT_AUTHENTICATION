package net.lavaguides.student_management_system_2.entity;

public class AuthenticationResponse {
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    private String token;
}