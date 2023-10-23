package com.shiropure.campuslink.controller;

import com.shiropure.campuslink.Form.IsTokenValidForm;
import com.shiropure.campuslink.Form.LoginForm;
import com.shiropure.campuslink.Form.RegisterForm;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.shiropure.campuslink.utils.DateTool.nextDay;
import static com.shiropure.campuslink.utils.TokenGenerator.generatorToken;

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
            //准备log和回文体
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"register successful");
            Log log = prepareLog("register", request);
            //检查form是否有全部字段，字段的内容是否合法
            if (!registerForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //检查email是否存在
            if(userRepo.userAlreadyExists(registerForm.getEmail())){
                res.setCodeAndMessage(400,"email already exists");
                log.setAdditionalInfo("email already exists");
                logRepo.save(log);
                return ResponseEntity.badRequest().body(res);
            }
            //创建并保存User
            User user = registerForm.createUser();
            userRepo.save(user);
            //创建token
            res.insertData("token",generateAndSaveToken(user.getUuid()));
            //异步发送激活账号邮件
            CompletableFuture.runAsync(() -> {
                emailService.sendRegistrySuccessfullyEmail(
                        user.getEmail(),
                        user.getUuid(),
                        user.getUsername(),
                        IPTool.getIP(request)
                );
            });
            //记录log 并 完成回文体
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
            //准备log和回文体
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"login successful");
            Log log = prepareLog("login", request);
            // 检查表单有效性
            if (!loginForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //检查用户名密码是否匹配
            Optional<User> user = userRepo.login(loginForm.getEmail(),loginForm.getPassword());
            if(!user.isPresent())
            {
                res.setCodeAndMessage(401,"username or password incorrect");
                log.setAdditionalInfo("username or password incorrect");
                logRepo.save(log);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }
            //创建token
            res.insertData("token",generateAndSaveToken(user.get().getUuid()));
            //更新最后登陆时间
            User user1 = user.get();
            user1.setLastLoginTime(new Date());
            userRepo.save(user1);
            //记录log 并 完成回文体
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
            //准备log和回文体
            ApiResponseObject res = new ApiResponseObject();
            res.setCodeAndMessage(200,"Token is valid");
            Log log = prepareLog("Token is valid", request);
            //检查form是否有全部字段，字段的内容是否合法
            if (!isTokenValidForm.isFormValid()) {
                return handleFormInvalid(res, log);
            }
            //检查token是否已经失效或者过期
            if (!tokenIsValid(isTokenValidForm.getToken())) {
                res.setCodeAndMessage(401, "Activation token is invalid or expired.");
                log.setAdditionalInfo("Activation token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            //记录log 并 完成回文体
            logRepo.save(log);
            return ResponseEntity.ok(res);
        }catch (Exception e){
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
//    private boolean tokenIsValid(String token) {
//        Optional<Token> tokenEntity = tokenRepo.findByToken(token);
//        Boolean result = tokenEntity.map(value -> value.isActive() && value.getExpiryDate().after(new Date())).orElse(false);
//        return result;
//    }
//    private boolean passResetTokenIsValid(String token) {
//        Optional<PassResetToken> passResetTokenEntity = passResetTokenRepo.findByToken(token);
//        return passResetTokenEntity.map(value -> value.isActive() && value.getExpireDate().after(new Date())).orElse(false);
//    }
}
