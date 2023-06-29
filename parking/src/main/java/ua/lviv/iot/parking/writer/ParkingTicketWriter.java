package ua.lviv.iot.parking.writer;

import ua.lviv.iot.parking.model.ParkingTicket;

public class ParkingTicketWriter extends CsvWriterAbstract<ParkingTicket> {
    private static final String CSV_HEADER = "Ticket Number,Car Number,Entry Time,Exit Time";

    @Override
    protected String getCsvHeader() {
        return CSV_HEADER;
    }

    @Override
    protected String getCsvRecord(ParkingTicket parkingTicket) {
        StringBuilder csvRecord = new StringBuilder();
        csvRecord.append(parkingTicket.getSpotNumber());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingTicket.getCarNumber());
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingTicket.getEntryTime().format(DATE_TIME_FORMATTER));
        csvRecord.append(CSV_SEPARATOR);
        csvRecord.append(parkingTicket.getExitTime().format(DATE_TIME_FORMATTER));
        return csvRecord.toString();
    }
}
