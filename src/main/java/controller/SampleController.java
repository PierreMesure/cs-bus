package controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.BusTime;
import pojo.Response;
import pojo.Transport;
import service.CsvGenerator;
import service.Trafiklab;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/date")
    @ResponseBody
    String date() {
        return "Time : " + new Date();
    }

    @RequestMapping("/nextBus")
    @ResponseBody
    String nextBus() throws IOException {
        Trafiklab trafiklab = new Trafiklab();

        Response response = trafiklab.getRealTimeDepartures("json", 9192, 60, true,
                true, true, true, true);

        if (ArrayUtils.contains(trafiklab.errorStatusCodes, response.getStatusCode())) {
            return "Trafiklab error" + response.getStatusCode() + ": " + response.getMessage();
        }

        List<BusTime> busTimes = BusTime.createFromResponse(response);

        Transport nextBus = response.getResponseData().getBuses().get(0);
        return "The next bus is coming in " + nextBus.getDisplayTime()
                + " at " + nextBus.getStopAreaName();
    }

    @RequestMapping("/writeBusTimes")
    @ResponseBody
    String writeBusTimes(int siteId) throws IOException {
        Trafiklab trafiklab = new Trafiklab();

        Response response = trafiklab.getRealTimeDepartures("json", siteId, 60, true,
                true, true, true, true);

        if (ArrayUtils.contains(Trafiklab.errorStatusCodes, response.getStatusCode())) {
            return "Trafiklab error" + response.getStatusCode() + ": " + response.getMessage();
        }

        List<BusTime> busTimes = BusTime.createFromResponse(response);

        CsvGenerator.writeBusTimesFile(busTimes, CsvGenerator.getPath());

        return "Bus times written.";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}