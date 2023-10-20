package com.example.campuslink.services;

import com.example.campuslink.entity.Calendar;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IcsToJsonConverterTest {

    @Test
    public void testConvertIcsToJson() {
        String icsUrl = "https://timetable.sydney.edu.au/odd/rest/calendar/ical/dd297019-48dc-439c-8f7b-36512e98b327";
        JSONObject JSONCalendar = IcsToJsonConverter.convertIcsToJson(icsUrl);
        try {
            //System.out.println(jsonCalendar.toString());
            JSONArray events = JSONCalendar.getJSONArray("events");
            JSONObject event = events.getJSONObject(1);
            System.out.println(event.toString());
        } catch (Exception e){

        }
        Calendar newCal = new Calendar(1,2,"test","https://timetable.sydney.edu.au/odd/rest/calendar/ical/dd297019-48dc-439c-8f7b-36512e98b327");
        newCal.loadFromJson(JSONCalendar);


        assertNotNull(JSONCalendar, "JSON Calendar should not be null");

        // Test JSON structure
        assertTrue(JSONCalendar.has("events"), "JSON Calendar should have 'events' property");
    }
}
