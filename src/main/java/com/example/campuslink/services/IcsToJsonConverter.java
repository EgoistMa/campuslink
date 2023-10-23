package com.example.campuslink.services;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarParserImpl;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

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
}
