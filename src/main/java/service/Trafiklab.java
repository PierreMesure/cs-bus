package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pierre on 01/08/2017.
 */
public class Trafiklab {
    private static String API_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public Trafiklab() {}

    public Response getRealTimeDepartures(String format, int siteId, int timeWindow, boolean bus, boolean metro, boolean train,
                                          boolean tram, boolean ship) {
        ObjectMapper mapper = new ObjectMapper();

        String urlString= "http://api.sl.se/api2/realtimedeparturesV4.%s?key=%s&siteid=%d&timewindow=%d";
        URL url = null;
        try {
            if (!bus) urlString += "&bus=false";
            if (!metro) urlString += "&metro=false";
            if (!train) urlString += "&train=false";
            if (!tram) urlString += "&tram=false";
            if (!ship) urlString += "&ship=false";
            url = new URL(String.format(urlString, format, API_KEY, siteId, timeWindow));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            return mapper.readValue(url, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
