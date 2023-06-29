package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.ParkingUserCard;

public class ParkingUserCardWriter extends CsvWriterAbstract<ParkingUserCard> {
    private static final String CSV_HEADER = "First name,Last name,Age,UserCard ID";

    @Override
    protected String getCsvHeader() {
        return CSV_HEADER;
    }

    @Override
    protected String getCsvRecord(ParkingUserCard parkingUserCard) {
        StringBuilder csvRecord = new StringBuilder();
        csvRecord.append(parkingUserCard.getFirstName());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingUserCard.getLastName());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingUserCard.getAge());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingUserCard.getUserCodeId());
        return csvRecord.toString();
    }
}

