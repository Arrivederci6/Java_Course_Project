package ua.lviv.iot.parking.reader;

import ua.lviv.iot.parking.model.Parking;

import java.time.format.DateTimeFormatter;

public class ParkingReader extends CsvReaderAbstract<Parking> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected Parking createInstance(String[] data) {
        var address = data[0];
        var marketNetwork = data[1];
        var countOfParkingSpots = Integer.parseInt(data[2]);

        return new Parking(address, marketNetwork, countOfParkingSpots);
    }
}

