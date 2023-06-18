package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.Parking;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingWriter {
    private static final String CSV_HEADER = "Address,Market Network,Count of parking spots";
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void writeDataToCsv(List<Parking> parkings, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            writer.write(CSV_HEADER);
            writer.write(System.lineSeparator());

            for (Parking parking : parkings) {
                writer.write(parking.getAddress());
                writer.write(CSV_SEPARATOR);
                writer.write(parking.getMarketNetwork());
                writer.write(CSV_SEPARATOR);
                writer.write(String.valueOf(parking.getCountOfParkingSpots()));
                writer.write(System.lineSeparator());
            }

            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing data to the CSV file.");
            e.printStackTrace();
        }
    }
}
