package ua.lviv.iot.parking.reader;

import ua.lviv.iot.parking.model.ParkingUserCard;

import java.time.format.DateTimeFormatter;

public class ParkingUserCardReader extends CsvReaderAbstract<ParkingUserCard> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected ParkingUserCard createInstance(String[] data) {
        var firstName = data[0];
        var lastName = data[1];
        var age = Integer.parseInt(data[2]);
        var userCodeId = Integer.parseInt(data[3]);

        return new ParkingUserCard(firstName, lastName, age, userCodeId);
    }
}