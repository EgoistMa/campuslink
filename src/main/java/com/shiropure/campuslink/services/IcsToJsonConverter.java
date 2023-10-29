package com.shiropure.campuslink.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shiropure.campuslink.entity.Event;
import com.shiropure.campuslink.entity.Task;
import com.shiropure.campuslink.entity.TimeTable;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IcsToJsonConverter {

    public static JSONObject convertIcsToJson(String icsUrl) {
        try {
            URL url = new URL(icsUrl);
            InputStream is = url.openStream();

            CalendarBuilder builder = new CalendarBuilder();

            Calendar calendar = builder.build(is);

            JsonObject jsonCalendar = convertCalendarToJson(calendar);
            JSONObject JSONCalendar = new JSONObject(jsonCalendar.toString());
            return JSONCalendar;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JsonObject convertCalendarToJson(Calendar calendar) {
        JsonObject jsonCalendar = new JsonObject();
        JsonArray jsonEvents = new JsonArray();

        for (Object component : calendar.getComponents()) {
            if (component instanceof Component) {
                JsonObject jsonEvent = new JsonObject();
                Component event = (Component) component;

                for (Property property : event.getProperties()) {
                    String propertyName = property.getName();
                    String propertyValue = property.getValue();

                    jsonEvent.addProperty(propertyName, propertyValue);
                }

                jsonEvents.add(jsonEvent);
            }
        }

        jsonCalendar.add("events", jsonEvents);
        return jsonCalendar;
    }

    public static Map<String, Map<String,String>> convertIcsToMap(String icsUrl) throws Exception {
        URL url = new URL(icsUrl);

        try (InputStream is = url.openStream()) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(is);

            return convertCalendarToMap(calendar);
        } catch (Exception e) {
            throw new RuntimeException("Error during conversion: " + e.getMessage(), e);
        }
    }

    private static Map<String, Map<String, String>> convertCalendarToMap(Calendar calendar) {
        Map<String, Map<String, String>> resultMap = new HashMap<>();

        for (Object component : calendar.getComponents()) {
            if (component instanceof VEvent) {
                VEvent event = (VEvent) component;
                String uid = event.getUid().getValue();

                Map<String, String> eventPropertiesMap = new HashMap<>();
                for (Property property : event.getProperties()) {
                    String propertyName = property.getName();
                    String propertyValue = property.getValue();
                    eventPropertiesMap.put(propertyName, propertyValue);
                }

                resultMap.put(uid, eventPropertiesMap);
            }
        }

        return resultMap;
    }

    public static List<Event> convertUrlToTask(String url) throws Exception {
        List<Event> result = new ArrayList<>();
        Map<String, Map<String,String>> data =  convertIcsToMap(url);
        for (String uid : data.keySet()) {
            Map<String, String> eventData = data.get(uid);

            Event event = new Event();
            event.setLOCATION(eventData.get("LOCATION"));
            event.setUID(eventData.get("UID"));
            event.setDTSTAMP(eventData.get("DTSTAMP"));
            event.setSUMMARY(eventData.get("SUMMARY"));
            event.setDTSTART(eventData.get("DTSTART"));
            event.setDESCRIPTION(eventData.get("DESCRIPTION"));
            event.setDTEND(eventData.get("DTEND"));

            result.add(event);
        }
        return result;
    }

}
