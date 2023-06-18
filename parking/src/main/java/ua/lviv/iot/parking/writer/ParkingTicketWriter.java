package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.ParkingTicket;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingTicketWriter {
    private static final String CSV_HEADER = "Ticket Number,Car Number,Entry Time,Exit Time";
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void writeDataToCsv(List<ParkingTicket> parkingTickets, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            writer.write(CSV_HEADER);
            writer.write(System.lineSeparator());

            for (ParkingTicket parkingTicket : parkingTickets) {
                writer.write(String.valueOf(parkingTicket.getSpotNumber()));
                writer.write(CSV_SEPARATOR);
                writer.write(parkingTicket.getCarNumber());
                writer.write(CSV_SEPARATOR);
                writer.write(parkingTicket.getEntryTime().format(DATE_TIME_FORMATTER));
                writer.write(CSV_SEPARATOR);
                writer.write(parkingTicket.getExitTime().format(DATE_TIME_FORMATTER));
                writer.write(System.lineSeparator());
            }

            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing data to the CSV file.");
            e.printStackTrace();
        }
    }
}
