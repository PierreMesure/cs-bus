package service;

import pojo.BusTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvGenerator {

    private static CsvGenerator instance = null;

    private CsvGenerator() {}

    public static CsvGenerator getInstance() {
        if(instance == null) {
            instance = new CsvGenerator();
        }

        return instance;
    }

    public static void writeBusTimesFile(List<BusTime> busTimes, String path) {

        String filename = "bus.csv";

        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        StringBuilder fileContent = new StringBuilder();

        for (BusTime busTime : busTimes) {
            fileContent.append(busTime.getLine()).append(",");
            fileContent.append(busTime.getDestinationA()).append(",");

            for (int i = 0; i < 3; i++) {
                fileContent.append(busTime.getBusTimeA(i)).append(",");
            }

            fileContent.append(busTime.getDestinationB()).append(",");

            for (int i = 0; i < 3; i++) {
                fileContent.append(busTime.getBusTimeB(i)).append(",");
            }
            fileContent.deleteCharAt(fileContent.length() - 1);

            fileContent.append("\n");
        }

        try {
            fileWriter = new FileWriter(new File(filename));
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

    public static String getPath() {
        return (String) ServerProperties.getInstance().getProperty("csv.import.path");
    }
}
