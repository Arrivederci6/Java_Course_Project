package ua.lviv.iot.parking.reader;

import ua.lviv.iot.parking.model.ParkingSpot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotReader {
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<ParkingSpot> readDataFromCsv(String csvFilePath) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();

        var file = new File(csvFilePath);
        if(!file.exists()){
            return parkingSpots;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            var isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                var data = line.split(CSV_SEPARATOR);

                var spotNumber = Integer.parseInt(data[0]);
                var carNumber = data[1];
                var entryTime = LocalDateTime.parse(data[2], DATE_TIME_FORMATTER);
                var exitTime = LocalDateTime.parse(data[3], DATE_TIME_FORMATTER);

                var parkingSpot = new ParkingSpot(spotNumber, carNumber, entryTime, exitTime);
                parkingSpots.add(parkingSpot);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading data from the CSV file.");
            e.printStackTrace();
        }

        return parkingSpots;
    }
}
