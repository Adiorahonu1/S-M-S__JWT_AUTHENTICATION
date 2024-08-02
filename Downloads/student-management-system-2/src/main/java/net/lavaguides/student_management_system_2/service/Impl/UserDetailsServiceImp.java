package net.lavaguides.student_management_system_2.service.Impl;
import net.lavaguides.student_management_system_2.entity.Users;
import net.lavaguides.student_management_system_2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: {}", username);
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warn("Username not found: {}", username);
                    return new UsernameNotFoundException("Username not found");
                });
        logger.info("User loaded: {}", user.getUsername());
        return user;
    }
}
//new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities())


