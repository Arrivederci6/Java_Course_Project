package ua.lviv.iot.parking.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class CsvWriterAbstract<T> {
    protected static final String CSV_SEPARATOR = ",";
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected abstract String getCsvHeader();
    protected abstract String getCsvRecord(T item);

    public void writeDataToCsv(List<T> items, String csvFilePath) {
        try (var writer = new FileWriter(csvFilePath)) {
            writer.write(getCsvHeader());
            writer.write(System.lineSeparator());

            for (var item : items) {
                writer.write(getCsvRecord(item));
                writer.write(System.lineSeparator());
            }

            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing data to the CSV file.");
            e.printStackTrace();
        }
    }
}