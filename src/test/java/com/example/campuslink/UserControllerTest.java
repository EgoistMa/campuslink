package com.example.campuslink;

import com.example.campuslink.Form.LoginForm;
import com.example.campuslink.Form.LogoutForm;
import com.example.campuslink.Form.RegisterForm;
import com.example.campuslink.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void register() throws Exception {
        RegisterForm rf = new RegisterForm("test","test-middle","test-last","test@domin.com","testpass","tester","googleToken","oktaToken");
        User before = rf.createUser();
        //first register
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rf)))
                .andExpect(status().isOk());
        //register same user again
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rf)))
                .andExpect(status().is(401));
        //register missing fields
        rf.setFirstName(null);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rf)))
                .andExpect(status().is(400));
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is("test@domin.com"));
        User after = mongoTemplate.findOne(query, User.class);

        Assertions.assertEquals(before.getEmail(), after.getEmail());
        //cleanup
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("email").is("test@domin.com"));
        mongoTemplate.remove(query2, User.class);
    }
    @Test
    public void login() throws Exception {
        RegisterForm rf = new RegisterForm("test","test-middle","test-last","test@domin.com","testpass","tester","googleToken","oktaToken");
        //first register
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rf)))
                .andExpect(status().isOk());

        LoginForm lf = new LoginForm("test@domin.com","testpass");
        //correct credentials
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().isOk());
        //wrong credentials
        lf.setPassword("wrongpass");
        lf.setEmail("wrongemail");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().is(401));
        //missing fields
        lf.setPassword(null);
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().is(400));

        //cleanup
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("email").is("test@domin.com"));
        mongoTemplate.remove(query2, User.class);
    }
    @Test
    public void logout() throws Exception {
        RegisterForm rf = new RegisterForm("test","test-middle","test-last","test@domin.com","testpass","tester","googleToken","oktaToken");
        //first register
        MvcResult result = mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rf)))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();

        String token = responseBody.substring(57,responseBody.length()-3);
        LogoutForm lf = new LogoutForm(token);
        //first logout
        mockMvc.perform(post("/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().isOk());
        //second logout
        mockMvc.perform(post("/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().is(401));
        //logout with wrong token
        lf.setToken("wrongtoken");
        mockMvc.perform(post("/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().is(401));
        //missing fields
        lf.setToken(null);
        mockMvc.perform(post("/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lf)))
                .andExpect(status().is(400));

        //cleanup
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("email").is("test@domin.com"));
        mongoTemplate.remove(query2, User.class);
    }

    @Test
    public void redirect() throws Exception {
        mockMvc.perform(post("/"))
                .andExpect(status().is(302));
    }
}
