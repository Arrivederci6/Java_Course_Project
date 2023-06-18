package ua.lviv.iot.parking.reader;

import ua.lviv.iot.parking.model.Parking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingReader {
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<Parking> readDataFromCsv(String csvFilePath) {
        List<Parking> parkings = new ArrayList<>();

        var file = new File(csvFilePath);
        if(!file.exists()){
            return parkings;
        }

        try (var reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            var isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                var data = line.split(CSV_SEPARATOR);

                var address = data[0];
                var marketNetwork = data[1];
                var countOfParkingSpots = Integer.parseInt(data[2]);

                var parking = new Parking(address, marketNetwork, countOfParkingSpots);
                parkings.add(parking);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading data from the CSV file.");
            e.printStackTrace();
        }

        return parkings;
    }
}
