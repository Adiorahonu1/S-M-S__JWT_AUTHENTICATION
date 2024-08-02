package net.lavaguides.student_management_system_2.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.lavaguides.student_management_system_2.entity.Token;
import net.lavaguides.student_management_system_2.repository.TokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    private  final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);

        //get stored token from database
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        // inValidate the id e.g make logout true
        if(token != null){
            storedToken.setLoggedout(true);
            tokenRepository.save(storedToken);
        }

    }
}
