package com.example.campuslink.controller;

import com.example.campuslink.Form.LogoutForm;
import com.example.campuslink.Form.RegisterForm;
import com.example.campuslink.entity.Token;
import com.example.campuslink.entity.User;
import com.example.campuslink.Form.LoginForm;
import com.example.campuslink.repository.TokenRepository;
import com.example.campuslink.repository.UserRepository;
import com.example.campuslink.utils.ApiResponseObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import io.swagger.annotations.ApiResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.example.campuslink.utils.DateTool.nextDay;
import static com.example.campuslink.utils.InputCheck.isNull;
import static com.example.campuslink.utils.TokenGenerator.generatorToken;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "User Controller", description = "Login operations")
public class UserController {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    TokenRepository tokenRepo;

    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/register")
    @CrossOrigin
    @ApiOperation(value = "register user")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "register successful"),
            @ApiResponse(code = 400, message = "Missing fields"),
            @ApiResponse(code = 401, message = "user already exists"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<ApiResponseObject> register(@RequestBody RegisterForm registerForm)
    {
        //todo add log
        ApiResponseObject apiResp = new ApiResponseObject();
        //check if form is legal
        if (registerForm.formNotLegal())  {
            apiResp.setCode(400);
            apiResp.setMsg("Missing fields");
            return ResponseEntity.status(400).body(apiResp);
        }
        //check if user already exists
        Optional<User> userObj = userRepo.findUserByEmail(registerForm.getEmail());
        if (userObj.isPresent()) {
            apiResp.setCode(401);
            apiResp.setMsg("user already exists");
            return ResponseEntity.status(401).body(apiResp);
        }
        //add user
        User user = registerForm.createUser();
        userRepo.save(user);
        //generator token
        String token = generatorToken(user.getUuid());
        tokenRepo.save(new Token(token,nextDay(new Date())));
        //todo add log
        //send response
        apiResp.setCode(200);
        apiResp.setMsg("register successful");
        apiResp.insertData("token",token);
        return ResponseEntity.ok(apiResp);
    }
    @PostMapping("/login")
    @CrossOrigin
    @ApiOperation(value = "Login user")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successful", response = ApiResponseObject.class),
            @ApiResponse(code = 400, message = "Missing username or password"),
            @ApiResponse(code = 401, message = "Invalid credentials"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<ApiResponseObject> login(@RequestBody LoginForm loginForm)
    {
        //todo add log
        ApiResponseObject apiResp = new ApiResponseObject();
        String email, password;
        //check if form is legal
        if (loginForm.formNotLegal()) {
            apiResp.setCode(400);
            apiResp.setMsg("Missing username or password");
            return ResponseEntity.status(400).body(apiResp);
        }
        email = loginForm.getEmail();

        //check if user exists
        Optional<UUID> uuid = userRepo.login(email,loginForm.getPassword());
        if (uuid.isEmpty()) {
            apiResp.setCode(401);
            apiResp.setMsg("Invalid credentials");
            return ResponseEntity.status(401).body(apiResp);
        }
        //user exists, generate and save token
        String token = generatorToken(uuid.get());
        tokenRepo.save(new Token(token, nextDay(new Date())));

        //send response
        apiResp.setCode(200);
        apiResp.setMsg("Login successful");
        apiResp.insertData("token", token);
        return ResponseEntity.ok(apiResp);
    }

    @PostMapping("/logout")
    @CrossOrigin
    @ApiOperation(value = "logout user")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "logout successful"),
            @ApiResponse(code = 400, message = "Missing token"),
            @ApiResponse(code = 401, message = "token dose not exists"),
            @ApiResponse(code = 401, message = "already logout"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<ApiResponseObject> logout(@RequestBody LogoutForm logoutForm){
        //todo add log
        ApiResponseObject apiResp = new ApiResponseObject();
        String token = logoutForm.getToken();
        //check if form is legal
        if (logoutForm.formNotLegal()) {
            apiResp.setCode(400);
            apiResp.setMsg("Missing token");
            return ResponseEntity.status(400).body(apiResp);
        }
        //check if token exists
        Optional<Token> tokenObj = tokenRepo.findTokenByToken(token);
        if (tokenObj.isEmpty()) {
            apiResp.setCode(401);
            apiResp.setMsg("token dose not exists");
            return ResponseEntity.status(401).body(apiResp);
        }
        //check if token is active
        if(!tokenObj.get().isActive()){
            apiResp.setCode(401);
            apiResp.setMsg("already logout");
            return ResponseEntity.status(401).body(apiResp);
        }
        //disable token
        tokenRepo.disableTokenByToken(tokenObj.get().getToken());
        //send response
        apiResp.setCode(200);
        apiResp.setMsg("Logout successful");
        return ResponseEntity.ok(apiResp);

    }
}
