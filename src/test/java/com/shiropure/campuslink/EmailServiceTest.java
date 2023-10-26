package com.shiropure.campuslink;

import com.example.campuslink.entity.Event;
import com.example.campuslink.services.IcsToJsonConverter;
import com.shiropure.campuslink.entity.User;
import com.shiropure.campuslink.repository.UserRepository;
import com.shiropure.campuslink.services.EmailService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
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
    @Test
    public void sendEventReminderTest(){
        String icsUrl = "https://timetable.sydney.edu.au/odd/rest/calendar/ical/dd297019-48dc-439c-8f7b-36512e98b327";
        com.example.campuslink.entity.Calendar testCal = new com.example.campuslink.entity.Calendar(1, 1, "test", icsUrl);
        Event testEvent = testCal.getNextEvent();

        Optional<User> user = userRepository.findUserByEmail("tianyuma4869@gmail.com");
        if(user.isPresent()){
            emailService.sendEventReminder(testEvent,user.get().getEmail(),user.get().getUuid(),user.get().getUsername(),"127.0.0.1");
        }
    }
}

