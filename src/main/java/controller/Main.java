package controller;

import org.apache.commons.lang3.ArrayUtils;
import pojo.BusTime;
import pojo.Response;
import service.CsvGenerator;
import service.Trafiklab;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trafiklab trafiklab = new Trafiklab();

        Response response = trafiklab.getRealTimeDepartures("json", Trafiklab.getSiteId(), 60, true,
                true, true, true, true);

        if (ArrayUtils.contains(Trafiklab.errorStatusCodes, response.getStatusCode())) {
            System.out.println("Trafiklab error" + response.getStatusCode() + ": " + response.getMessage());
        }

        List<BusTime> busTimes = BusTime.createFromResponse(response);

        CsvGenerator.writeBusTimesFile(busTimes, CsvGenerator.getPath());

        System.out.println("Bus times written.");
    }
}
