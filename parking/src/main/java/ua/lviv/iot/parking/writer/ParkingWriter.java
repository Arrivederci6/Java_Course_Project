package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.Parking;

public class ParkingWriter extends CsvWriterAbstract<Parking> {
    private static final String CSV_HEADER = "Address,Market Network,Count of parking spots";

    @Override
    protected String getCsvHeader() {
        return CSV_HEADER;
    }

    @Override
    protected String getCsvRecord(Parking parking) {
        StringBuilder csvRecord = new StringBuilder();
        csvRecord.append(parking.getAddress());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parking.getMarketNetwork());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parking.getCountOfParkingSpots());
        return csvRecord.toString();
    }
}

