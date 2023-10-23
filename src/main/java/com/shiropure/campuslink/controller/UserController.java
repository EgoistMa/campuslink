package com.shiropure.campuslink.controller;

import com.shiropure.campuslink.Form.*;
import com.shiropure.campuslink.entity.*;
import com.shiropure.campuslink.repository.LogRepository;
import com.shiropure.campuslink.repository.PassResetTokenRepository;
import com.shiropure.campuslink.repository.TokenRepository;
import com.shiropure.campuslink.repository.UserRepository;
import com.shiropure.campuslink.utils.ApiResponseObject;
import com.shiropure.campuslink.services.EmailService;
import com.shiropure.campuslink.utils.IPTool;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.shiropure.campuslink.utils.DateTool.nextDay;
import static com.shiropure.campuslink.utils.TokenGenerator.generatorToken;
import static com.shiropure.campuslink.utils.TokenGenerator.getUUIDFromToken;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "User Controller", description = "Login,logout,register operations")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    TokenRepository tokenRepo;

    @Autowired
    LogRepository logRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    PassResetTokenRepository passResetTokenRepo;
    @PostMapping("/register")
    public ResponseEntity<ApiResponseObject> register(@RequestBody RegisterForm registerForm, HttpServletRequest request){
        try{
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"register successful");
            Log log = prepareLog("register", request);
            //check form valid
            if (!registerForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check email already exists
            if(userRepo.userAlreadyExists(registerForm.getEmail())){
                res.setCodeAndMessage(400,"email already exists");
                log.setAdditionalInfo("email already exists");
                logRepo.save(log);
                return ResponseEntity.badRequest().body(res);
            }
            //create user and save
            User user = registerForm.createUser();
            userRepo.save(user);
            //create token and save
            res.insertData("token",generateAndSaveToken(user.getUuid()));
            //send email to user asynchronously
            CompletableFuture.runAsync(() -> {
                emailService.sendRegistrySuccessfullyEmail(
                        user.getEmail(),
                        user.getUuid(),
                        user.getUsername(),
                        IPTool.getIP(request)
                );
            });
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res) ;
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500,"internal server error"));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponseObject> login(@RequestBody LoginForm loginForm, HttpServletRequest request){
        try{
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"login successful");
            Log log = prepareLog("login", request);
            // check form valid
            if (!loginForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check user exists and password correct
            Optional<User> user = userRepo.login(loginForm.getEmail(),loginForm.getPassword());
            if(!user.isPresent())
            {
                res.setCodeAndMessage(401,"username or password incorrect");
                log.setAdditionalInfo("username or password incorrect");
                logRepo.save(log);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }
            //create token and save
            res.insertData("token",generateAndSaveToken(user.get().getUuid()));
            //update last login time
            User user1 = user.get();
            user1.setLastLoginTime(new Date());
            userRepo.save(user1);
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500,"internal server error"));
        }
    }
    @PostMapping("/isTokenValid")
    public ResponseEntity<ApiResponseObject> isTokenValid(@RequestBody IsTokenValidForm isTokenValidForm, HttpServletRequest request){
        try {
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"Token is valid");
            Log log = prepareLog("Token is valid", request);
            //check form valid
            if (!isTokenValidForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check token is valid
            if (!tokenIsValid(isTokenValidForm.getToken())) {
                res.setCodeAndMessage(401, "token is invalid or expired.");
                log.setAdditionalInfo("token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponseObject> logout(@RequestBody LogoutForm logoutForm, HttpServletRequest request){
        try{
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"logout successful");
            Log log = new Log(LogLevel.INFO,"logout",new Date(), IPTool.getIP(request));
            //check form valid
            if (!logoutForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check token is valid
            if(!tokenIsValid(logoutForm.getToken())){
                res.setCodeAndMessage(200,"already logout");
                log.setAdditionalInfo("already logout");
                logRepo.save(log);
                return ResponseEntity.status(200).body(res);
            }
            //disable token
            tokenRepo.disableByToken(logoutForm.getToken());
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500,"internal server error"));
        }
    }
    @PostMapping("/sendForgetPassEmail")
    public ResponseEntity<ApiResponseObject> sendForgetPassEmail(@RequestBody SendForgetPassEmailForm sendForgetPassEmailForm, HttpServletRequest request){
        try {
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"send Forget Password Email successful");
            Log log = prepareLog("send Forget Password Email", request);
            //check form valid
            if (!sendForgetPassEmailForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check email exists
            if(!userRepo.userAlreadyExists(sendForgetPassEmailForm.getEmail())){
                res.setCodeAndMessage(400,"email not exists");
                log.setAdditionalInfo("email not exists");
                logRepo.save(log);
                return ResponseEntity.badRequest().body(res);
            }
            //send email to user asynchronously
            Optional<User> user = userRepo.findUserByEmail(sendForgetPassEmailForm.getEmail());
            User user1 = user.get();
            CompletableFuture.runAsync(() -> {
                emailService.sendRestPassEmail(
                        user1.getEmail(),
                        user1.getUuid(),
                        user1.getUsername(),
                        IPTool.getIP(request)
                );
            });
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res) ;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    @PostMapping("/resetPass")
    public ResponseEntity<ApiResponseObject> resetPass(@RequestBody ResetPassForm forgetPass, HttpServletRequest request){
        try {
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"reset password successful");
            Log log = prepareLog("reset password successful", request);
            //check form valid
            if (!forgetPass.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check token is valid
            if (!passResetTokenIsValid(forgetPass.getForgetPassToken())) {
                res.setCodeAndMessage(400, "password reset token is invalid or expired.");
                log.setAdditionalInfo("Invalid or expired password reset token");
                logRepo.save(log);
                return ResponseEntity.badRequest().body(res);
            }

            PassResetToken token = passResetTokenRepo.findByToken(forgetPass.getForgetPassToken()).get();
            token.setActive(false);
            passResetTokenRepo.save(token);
            //reset password
            Optional<User> user = userRepo.findUserByUuid(UUID.fromString(getUUIDFromToken(forgetPass.getForgetPassToken()).get()));
            User user1 = user.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(forgetPass.getPassword());
            user1.setPassword(hashedPassword);
            userRepo.save(user1);
            //disable all token
            tokenRepo.disableByUuid(user1.getUuid());
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res) ;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    @PostMapping("/checkUserType")
    public ResponseEntity<ApiResponseObject> checkUserType(@RequestBody CheckUserTypeForm checkUserTypeForm, HttpServletRequest request){
        try {
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"Token is valid");
            Log log = prepareLog("Token is valid", request);
            //check form valid
            if (!checkUserTypeForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check token is valid
            if (!tokenIsValid(checkUserTypeForm.getToken())) {
                res.setCodeAndMessage(401, "token is invalid or expired.");
                log.setAdditionalInfo("token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            //check user exists and password correct
            Optional<User> user = userRepo.findUserByUuid(UUID.fromString(getUUIDFromToken(checkUserTypeForm.getToken()).get()));
            User user1 = user.get();
            res.insertData("userType",user1.getRole());
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    @PostMapping("/getUserDetails")
    public ResponseEntity<ApiResponseObject> getUserDetails(@RequestBody CheckUserTypeForm checkUserTypeForm, HttpServletRequest request){
        try {
            //prepare log and response body
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"User Authenticated");
            Log log = prepareLog("User Authenticated", request);
            //check form valid
            if (!checkUserTypeForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //check token is valid
            if (!tokenIsValid(checkUserTypeForm.getToken())) {
                res.setCodeAndMessage(401, "token is invalid or expired.");
                log.setAdditionalInfo("token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            //check user exists and password correct
            Optional<User> user = userRepo.findUserByUuid(UUID.fromString(getUUIDFromToken(checkUserTypeForm.getToken()).get()));
            User user1 = user.get();
            res.insertData("avatar",user1.getAvatar());
            res.insertData("username",user1.getUsername());
            //log and return response
            logRepo.save(log);
            return ResponseEntity.ok(res);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    private String generateAndSaveToken(UUID uuid){
        String token = generatorToken(uuid);
        tokenRepo.save(new Token(token,nextDay(new Date())));
        return token;
    }

    private Log prepareLog(String action, HttpServletRequest request) {
        return new Log(LogLevel.INFO, action, new Date(), IPTool.getIP(request));
    }

    private ResponseEntity<ApiResponseObject> handleFormInvalid(ApiResponseObject res, Log log) {
        res.setCodeAndMessage(400, "form invalid");
        log.setAdditionalInfo("form invalid");
        logRepo.save(log);
        return ResponseEntity.badRequest().body(res);
    }
    private boolean tokenIsValid(String token) {
        Optional<Token> tokenEntity = tokenRepo.findByToken(token);
        Boolean result = tokenEntity.map(value -> value.isActive() && value.getExpiryDate().after(new Date())).orElse(false);
        return result;
    }
    private boolean passResetTokenIsValid(String token) {
        Optional<PassResetToken> passResetTokenEntity = passResetTokenRepo.findByToken(token);
        return passResetTokenEntity.map(value -> value.isActive() && value.getExpireDate().after(new Date())).orElse(false);
    }
}
