package com.shiropure.campuslink.services;

import com.shiropure.campuslink.entity.Event;
import com.shiropure.campuslink.entity.Setting;
import com.shiropure.campuslink.entity.TimeTable;
import com.shiropure.campuslink.entity.User;
import com.shiropure.campuslink.repository.SettingRepository;
import com.shiropure.campuslink.repository.TimeTableRepository;
import com.shiropure.campuslink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class NotificationScheduledTasks {

    @Autowired
    TimeTableRepository timeTableRepo;
    @Autowired
    UserRepository userRepo;

    @Autowired
    SettingRepository settingRepo;

    @Scheduled(fixedRate = 3600000) // 3600000 milliseconds is 1 hour
    public void runEveryHour() {
        List<TimeTable> timeTables = timeTableRepo.findAll();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        for (TimeTable timeTable : timeTables) {
            List<Event> events = timeTable.getEvents();
            Optional<User> user = userRepo.findUserByUuid(timeTable.getOwner());

            if (!user.isPresent()) continue;

            long deltaTimeMillis = getDeltaTimeMillis(user.get(), timeFormat);
            if (deltaTimeMillis <= 0) continue;  // move to next timeTable if email is not enabled or deltaTime is invalid

            checkAndPrintMessage( user.get() ,events, deltaTimeMillis);
        }
    }

    private long getDeltaTimeMillis(User user, SimpleDateFormat timeFormat) {
        Optional<Setting> settingOptional = settingRepo.findById(user.getUuid().toString());

        if (settingOptional.isPresent() && settingOptional.get().getEnabledEmail()) {
            try {
                // Assuming setting.getTime() returns a Date object that represents only time
                Date deltaTimeDate = settingOptional.get().getTime();
                return deltaTimeDate.getTime() - timeFormat.parse("00:00:00").getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return -1;  // Return a negative value indicating email not enabled or error
    }
    private Date parseDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // adjust the format as needed
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void checkAndPrintMessage(User user,List<Event> events, long deltaTimeMillis) {
        Date now = new Date();
        for (Event event : events) {
            // Assuming event.getDate() returns the start date/time of the event
            Date eventDate = parseDateString(event.getDTSTART());

            if (eventDate == null) continue;
            long diff = eventDate.getTime() - now.getTime();

            if (0 <= diff && diff <= deltaTimeMillis) {
                event.sendMessage(user.getEmail());
            }
        }
    }

}
