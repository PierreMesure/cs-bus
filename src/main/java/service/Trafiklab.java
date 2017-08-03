package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;
import pojo.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pierre on 01/08/2017.
 */
public class Trafiklab {

    public static final int[] errorStatusCodes = {1001, 1002, 1003, 1004, 1005, 1006,
            1007, 4001, 5321, 5322, 5323, 5324};

    public Trafiklab() {
    }

    public Response getRealTimeDepartures(String format, int siteId, int timeWindow, boolean bus, boolean metro, boolean train,
                                          boolean tram, boolean ship) {
        ObjectMapper mapper = new ObjectMapper();

        String urlString = "http://api.sl.se/api2/realtimedeparturesV4.%s?key=%s&siteid=%d&timewindow=%d";
        URL url = null;
        try {
            if (!bus) urlString += "&bus=false";
            if (!metro) urlString += "&metro=false";
            if (!train) urlString += "&train=false";
            if (!tram) urlString += "&tram=false";
            if (!ship) urlString += "&ship=false";
            url = new URL(String.format(urlString, format, getApiKey(), siteId, timeWindow));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            Response response = mapper.readValue(url, Response.class);

            if (response == null || ArrayUtils.contains(errorStatusCodes, response.getStatusCode())) {
                System.out.println("Error" + response.getStatusCode() + ": " + response.getMessage());
            }

            return response;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getApiKey() {
        return (String) ServerProperties.getInstance().getProperty("trafiklab.api.key");
    }
}
