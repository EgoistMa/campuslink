package com.shiropure.campuslink.controller;

import com.shiropure.campuslink.Form.AddCalendarUrlForm;
import com.shiropure.campuslink.Form.UpdateSettingsForm;
import com.shiropure.campuslink.entity.*;
import com.shiropure.campuslink.repository.LogRepository;
import com.shiropure.campuslink.repository.SettingRepository;
import com.shiropure.campuslink.repository.TimeTableRepository;
import com.shiropure.campuslink.repository.TokenRepository;
import com.shiropure.campuslink.repository.impl.TokenRepositoryImpl;
import com.shiropure.campuslink.services.IcsToJsonConverter;
import com.shiropure.campuslink.utils.ApiResponseObject;
import com.shiropure.campuslink.utils.IPTool;
import com.shiropure.campuslink.utils.TokenGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "Calendar Controller", description = "handle Calendar")
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    TokenRepository tokenRepo;
    @Autowired
    LogRepository logRepo;

    @Autowired
    TimeTableRepository timeTableRepo;

    @Autowired
    SettingRepository settingRepo;

    @GetMapping("/getCalendar")
    public ResponseEntity<ApiResponseObject> getCalendar(String token , HttpServletRequest request) throws Exception {

        ApiResponseObject res = new ApiResponseObject();
        Log log = new Log(LogLevel.INFO, "get Calendar", new Date(), IPTool.getIP(request));
        //mock up
        String url = "https://timetable.sydney.edu.au/odd/rest/calendar/ical/b5811e2e-81ad-4efd-b1e2-7a78044d0c7c";
        Map<String,Map<String,String>> result = IcsToJsonConverter.convertIcsToMap(url);
        System.out.println(result);
        //mock up end

        //check token is valid
        if (token == null || !tokenIsValid(token)) {
            res.setCodeAndMessage(401, "token is invalid or expired.");
            log.setAdditionalInfo("token is invalid or expired.");
            logRepo.save(log);
            return ResponseEntity.status(401).body(res);
        }
        String uuid = TokenGenerator.getUUIDFromToken(token).get();
        TimeTable timeTable = timeTableRepo.findByOwnerUUID(UUID.fromString(uuid));

        res.setCode(200);
        res.setMsg("calendar object");
        res.insertData("calendar",timeTable);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/addCalendarUrl")
    public ResponseEntity<ApiResponseObject> addCalendarUrl(@RequestBody AddCalendarUrlForm addCalendarUrlForm , HttpServletRequest request) throws Exception {
        try {

            ApiResponseObject res = new ApiResponseObject();
            Log log = new Log(LogLevel.INFO, "add Calendar", new Date(), IPTool.getIP(request));

            //check token is valid
            if (addCalendarUrlForm.getToken() == null || !tokenIsValid(addCalendarUrlForm.getToken())) {
                System.out.println(addCalendarUrlForm.getToken());
                res.setCodeAndMessage(401, "token is invalid or expired.");
                log.setAdditionalInfo("token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            String uuid = TokenGenerator.getUUIDFromToken(addCalendarUrlForm.getToken()).get();
            TimeTable timeTable = timeTableRepo.findByOwnerUUID(UUID.fromString(uuid));
            if (timeTable == null){
                timeTable = new TimeTable(UUID.fromString(uuid));
            }
            List<Event> events = IcsToJsonConverter.convertUrlToTask(addCalendarUrlForm.getUrl());
            timeTable.setUrl(addCalendarUrlForm.getUrl());
            timeTable.setEvents(events);
            timeTableRepo.save(timeTable);
            res.setCode(200);
            res.setMsg("successfully added");
            return ResponseEntity.ok().body(res);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    @PostMapping("/updateSettings")
    public ResponseEntity<ApiResponseObject> updateSettings(@RequestBody UpdateSettingsForm updateSettingsForm , HttpServletRequest request) throws Exception {
        try {
            ApiResponseObject res = new ApiResponseObject();
            Log log = new Log(LogLevel.INFO, "add Calendar", new Date(), IPTool.getIP(request));

            //check token is valid
            if (updateSettingsForm.getToken() == null || !tokenIsValid(updateSettingsForm.getToken())) {
                System.out.println(updateSettingsForm.getToken());
                res.setCodeAndMessage(401, "token is invalid or expired.");
                log.setAdditionalInfo("token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            String uuid = TokenGenerator.getUUIDFromToken(updateSettingsForm.getToken()).get();

            Optional<Setting> setting = settingRepo.findById(uuid);
            if(setting.isEmpty()){
                setting = Optional.of(new Setting(UUID.fromString(uuid), updateSettingsForm.getReminder(), updateSettingsForm.getTime()));
                settingRepo.save(setting.get());
            }
            System.out.println(setting.toString());
            setting.get().setTime(updateSettingsForm.getTime());
            setting.get().setEnabledEmail(updateSettingsForm.getReminder());
            settingRepo.save(setting.get());
            res.setCode(200);
            res.setMsg("successfully updated");
            return ResponseEntity.ok().body(res);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }
    @GetMapping("/getSettings")
    public ResponseEntity<ApiResponseObject> getSettings(String token,HttpServletRequest request){
        try{
            ApiResponseObject res = new ApiResponseObject();
            Log log = new Log(LogLevel.INFO, "add Calendar", new Date(), IPTool.getIP(request));

            //check token is valid
            if (token == null || !tokenIsValid(token)) {
                System.out.println(token);
                res.setCodeAndMessage(401, "token is invalid or expired.");
                log.setAdditionalInfo("token is invalid or expired.");
                logRepo.save(log);
                return ResponseEntity.status(401).body(res);
            }
            String uuid = TokenGenerator.getUUIDFromToken(token).get();
            Optional<Setting> setting = settingRepo.findByUUid(uuid);
            System.out.println(setting.toString());
            if(setting.isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                setting = Optional.of(new Setting(UUID.fromString(uuid), false, calendar.getTime()));
                settingRepo.save(setting.get());
            }
            res.insertData("setting",setting);
            res.setCode(200);
            res.setMsg("successfully updated");
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponseObject(500, "internal server error"));
        }
    }

    private boolean tokenIsValid(String token) {
        Optional<Token> tokenEntity = tokenRepo.findByToken(token);
        Boolean result = tokenEntity.map(value -> value.isActive() && value.getExpiryDate().after(new Date())).orElse(false);
        return result;
    }

}
