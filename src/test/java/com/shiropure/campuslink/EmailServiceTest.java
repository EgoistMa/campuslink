package com.shiropure.campuslink;

import com.shiropure.campuslink.entity.User;
import com.shiropure.campuslink.repository.UserRepository;
import com.shiropure.campuslink.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;
    @Test
    public void sendEmail(){
        Optional<User> user = userRepository.findUserByEmail("tianyuma4869@gmail.com");
            if(user.isPresent()){
                emailService.sendRegistrySuccessfullyEmail(user.get().getEmail(),user.get().getUuid(),user.get().getUsername(),"127.0.0.1");
            }
    }
}

