package ua.lviv.iot.parking.reader;

import ua.lviv.iot.parking.model.ParkingSpot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingSpotReader extends CsvReaderAbstract<ParkingSpot> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected ParkingSpot createInstance(String[] data) {
        var spotNumber = Integer.parseInt(data[0]);
        var carNumber = data[1];
        var entryTime = LocalDateTime.parse(data[2], DATE_TIME_FORMATTER);
        var exitTime = LocalDateTime.parse(data[3], DATE_TIME_FORMATTER);

        return new ParkingSpot(spotNumber, carNumber, entryTime, exitTime);
    }
}
