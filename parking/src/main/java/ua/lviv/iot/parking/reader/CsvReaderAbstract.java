package ua.lviv.iot.parking.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvReaderAbstract<T> {
    private static final String CSV_SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected abstract T createInstance(String[] data);

    public List<T> readDataFromCsv(String csvFilePath) {
        List<T> dataList = new ArrayList<>();

        var file = new File(csvFilePath);
        if (!file.exists()) {
            return dataList;
        }

        try (var reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            var isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                var data = line.split(CSV_SEPARATOR);
                var instance = createInstance(data);
                dataList.add(instance);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading data from the CSV file.");
            e.printStackTrace();
        }

        return dataList;
    }
}
