package com.example.campuslink.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.biweekly.Biweekly;
import net.sf.biweekly.ICalendar;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class IcsToJsonFileConverter {

    public Path convertIcsUrlToJsonFile(String url, Path outputPath) throws IOException, InterruptedException {
        // Fetch the ICS file
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the ICS content
        ICalendar ical = Biweekly.parse(response.body()).first();

        // Convert to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ical);

        // Write to the specified output path
        Files.writeString(outputPath, json);

        return outputPath;
    }

    public static void main(String[] args) {
        IcsToJsonFileConverter converter = new IcsToJsonFileConverter();
        try {
            String url = "https://timetable.sydney.edu.au/odd/rest/calendar/ical/dd297019-48dc-439c-8f7b-36512e98b327";
            Path outputPath = Path.of("icsData.json");
            converter.convertIcsUrlToJsonFile(url, outputPath);
            System.out.println("JSON saved to: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
