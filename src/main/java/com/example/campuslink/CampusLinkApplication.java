package com.example.campuslink;

import com.example.campuslink.entity.User;
import com.example.campuslink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CampusLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusLinkApplication.class, args);
    }

}
