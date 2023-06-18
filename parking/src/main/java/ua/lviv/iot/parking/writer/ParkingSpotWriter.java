package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.ParkingSpot;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingSpotWriter {
    private static final String CSV_HEADER = "Spot Number,Car Number,Entry Time,Exit Time";
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void writeDataToCsv(List<ParkingSpot> parkingSpots, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            writer.write(CSV_HEADER);
            writer.write(System.lineSeparator());

            for (ParkingSpot parkingSpot : parkingSpots) {
                writer.write(String.valueOf(parkingSpot.getSpotNumber()));
                writer.write(CSV_SEPARATOR);
                writer.write(parkingSpot.getCarNumber());
                writer.write(CSV_SEPARATOR);
                writer.write(parkingSpot.getEntryTime().format(DATE_TIME_FORMATTER));
                writer.write(CSV_SEPARATOR);
                writer.write(parkingSpot.getExitTime().format(DATE_TIME_FORMATTER));
                writer.write(System.lineSeparator());
            }

            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing data to the CSV file.");
            e.printStackTrace();
        }
    }
}
