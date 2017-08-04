package service;

import pojo.BusTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvGenerator {

    private static CsvGenerator instance = null;

    protected CsvGenerator() {}

    public static CsvGenerator getInstance() {
        if(instance == null) {
            instance = new CsvGenerator();
        }

        return instance;
    }

    public static void writeBusTimesFile(List<BusTime> busTimes, String path) {

        String filename = path + "bus.csv";

        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        StringBuilder fileContent = new StringBuilder();

        for (BusTime busTime : busTimes) {
            String busTimeString =
                    busTime.getLine() + ","
                            + busTime.getDestinationA() + ","
                            + busTime.getBusTimesA().get(0) + ","
                            + busTime.getBusTimesA().get(1) + ","
                            + busTime.getBusTimesA().get(2) + ","
                            + busTime.getDestinationB() + ","
                            + busTime.getBusTimesB().get(0) + ","
                            + busTime.getBusTimesB().get(1) + ","
                            + busTime.getBusTimesB().get(2) + ",";
            fileContent.append(busTimeString).append("\n");
        }

        try {
            fileWriter = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
