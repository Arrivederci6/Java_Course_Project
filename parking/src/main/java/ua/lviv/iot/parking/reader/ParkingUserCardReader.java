package ua.lviv.iot.parking.reader;

import ua.lviv.iot.parking.model.ParkingUserCard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingUserCardReader {
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<ParkingUserCard> readDataFromCsv(String csvFilePath) {
        List<ParkingUserCard> parkingUserCards = new ArrayList<>();

        var file = new File(csvFilePath);
        if(!file.exists()){
            return parkingUserCards;
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

                var firstName = data[0];
                var lastName = data[1];
                var age = Integer.parseInt(data[2]);
                var userCodeId = Integer.parseInt(data[3]);

                var parkingUserCard = new ParkingUserCard(firstName, lastName, age, userCodeId);
                parkingUserCards.add(parkingUserCard);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading data from the CSV file.");
            e.printStackTrace();
        }

        return parkingUserCards;
    }
}
