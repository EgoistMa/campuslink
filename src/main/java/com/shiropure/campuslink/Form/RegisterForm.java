package com.shiropure.campuslink.Form;

import com.shiropure.campuslink.entity.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class RegisterForm extends Form {
    @ApiModelProperty(value = "firstname",required = true,example = "Tianyu")
    private String firstname;
    @ApiModelProperty(value = "lastname",required = true,example = "Ma")
    private String lastname;
    @ApiModelProperty(value = "username",required = true,example = "TianyuM")
    private String username;
    @ApiModelProperty(value = "email",required = true,example = "tianyuma4869@gmail.com")
    private String email;
    @ApiModelProperty(value = "password",required = true,example = "123456")
    private String password;

    public RegisterForm(String firstname, String lastname, String username, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isFormValid() {
        if (areAnyFieldsNull(firstname, lastname, username, email, password)) {
            System.out.println("firstname, lastname, username, email, role, password is null");
            return false;
        }

        if (!isEmailValid(email)) {
            System.out.println("bad email");
            return false;
        }

        return true;
    }
    public User createUser() {
        //hash password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        return new User(UUID.randomUUID(),
                username,
                email,
                "user",
                hashedPassword,
                firstname,
                lastname,
                new Date(),
                true
                );
    }
}
