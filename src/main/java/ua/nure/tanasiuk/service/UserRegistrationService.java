package ua.nure.tanasiuk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.nure.tanasiuk.model.UserIdentity;

@Service
public class UserRegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @Value("${auth.host}${auth.login-via-email}")
    private String loginUrl;

    public UserRegistrationService(PasswordEncoder passwordEncoder,
                                   UserService userService,
                                   RestTemplate restTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public UserIdentity registerUser(UserIdentity userRegistrationData) {
        String encodedPassword = passwordEncoder.encode(userRegistrationData.getPassword());
        userRegistrationData.setPassword(encodedPassword);

        return userService.create(userRegistrationData);
    }
}
