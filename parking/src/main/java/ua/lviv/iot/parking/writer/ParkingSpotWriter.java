package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.ParkingSpot;

public class ParkingSpotWriter extends CsvWriterAbstract<ParkingSpot> {
    private static final String CSV_HEADER = "Spot Number,Car Number,Entry Time,Exit Time";

    @Override
    protected String getCsvHeader() {
        return CSV_HEADER;
    }

    @Override
    protected String getCsvRecord(ParkingSpot parkingSpot) {
        StringBuilder csvRecord = new StringBuilder();
        csvRecord.append(parkingSpot.getSpotNumber());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingSpot.getCarNumber());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingSpot.getEntryTime().format(DATE_TIME_FORMATTER));
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingSpot.getExitTime().format(DATE_TIME_FORMATTER));
        return csvRecord.toString();
    }
}

