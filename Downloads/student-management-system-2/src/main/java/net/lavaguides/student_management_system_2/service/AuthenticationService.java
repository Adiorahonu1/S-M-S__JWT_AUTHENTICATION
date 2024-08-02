package net.lavaguides.student_management_system_2.service;

import net.lavaguides.student_management_system_2.entity.AuthenticationResponse;
import net.lavaguides.student_management_system_2.entity.Token;
import net.lavaguides.student_management_system_2.entity.Users;
import net.lavaguides.student_management_system_2.repository.TokenRepository;
import net.lavaguides.student_management_system_2.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {




    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, Jwt_service jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    private final PasswordEncoder passwordEncoder;
    private final Jwt_service jwtService;

    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse register(Users request){
        Users user = new Users();
        user.setFistName(request.getFistName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        //save the generated token
        saveUserToken(user, token);


        return new AuthenticationResponse(token);
    }



    public AuthenticationResponse authenticate(Users request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        saveUserToken(user, token);

//        revokeAllTokenByUser(user);
        return new AuthenticationResponse(token);
    }
    private void saveUserToken(Users user, String token) {

        Token token1 = new Token();
        token1.setToken(token);
        token1.setLoggedout(false);
        token1.setUser(user);
        tokenRepository.save(token1);
    }

//    private void revokeAllTokenByUser(Users user) {
//        List<Token> validTokenListByUser = tokenRepository.findAllTokenByUser(user.getId());
//        if(!validTokenListByUser.isEmpty()){
//            validTokenListByUser.forEach(t->
//            {t.setLoggedout(true);});
//
//        }
//
//        tokenRepository.saveAll(validTokenListByUser);
//    }
}