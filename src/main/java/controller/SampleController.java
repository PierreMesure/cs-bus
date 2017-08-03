package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Response;
import pojo.Transport;
import service.Trafiklab;

import java.io.IOException;
import java.util.Date;

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

        Response response = trafiklab.getRealTimeDepartures("json", 3703, 60, true,
                true, true, true, true);
        Transport nextBus = response.getResponseData().getBuses().get(0);
        return "The next bus is coming in " + nextBus.getDisplayTime()
                + " at " + nextBus.getStopAreaName();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}