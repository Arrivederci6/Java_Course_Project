package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.ParkingUserCard;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingUserCardWriter {
    private static final String CSV_HEADER = "First name,Last name,Age,UserCard ID";
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void writeDataToCsv(List<ParkingUserCard> parkingUserCards, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            writer.write(CSV_HEADER);
            writer.write(System.lineSeparator());

            for (ParkingUserCard parkingUserCard : parkingUserCards) {
                writer.write(parkingUserCard.getFirstName());
                writer.write(CSV_SEPARATOR);
                writer.write(parkingUserCard.getLastName());
                writer.write(CSV_SEPARATOR);
                writer.write(String.valueOf(parkingUserCard.getAge()));
                writer.write(CSV_SEPARATOR);
                writer.write(String.valueOf(parkingUserCard.getUserCodeId()));
                writer.write(System.lineSeparator());
            }

            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing data to the CSV file.");
            e.printStackTrace();
        }
    }
}
