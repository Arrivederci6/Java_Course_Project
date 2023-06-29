package ua.lviv.iot.parking.dataaccess;

import org.springframework.stereotype.Repository;
import ua.lviv.iot.parking.model.Parking;
import ua.lviv.iot.parking.reader.ParkingReader;
import ua.lviv.iot.parking.writer.ParkingWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ParkingRepository {
    private Map<Integer, Parking> parkings = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();
    private final String csvFilePath = "parking-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".csv";
    private ParkingWriter parkingWriter = new ParkingWriter();
    private ParkingReader parkingReader = new ParkingReader();

    public List<Parking> getParkings() {
        return new LinkedList<>(parkings.values());
    }

    public Parking getParking(Integer parkingId) {
        return parkings.get(parkingId);
    }

    public Parking createParking(Parking parking) {
        parking.setId(idCounter.incrementAndGet());
        parkings.put(parking.getId(), parking);
        saveDataToCsv();
        return parking;
    }

    public boolean deleteParking(Integer parkingId) {
        var removed = parkings.remove(parkingId) != null;
        if (removed) {
            saveDataToCsv();
        }
        return removed;
    }

    public Parking updateParking(Integer parkingId, Parking updatedParking) {
        if (parkings.containsKey(parkingId)) {
            updatedParking.setId(parkingId);
            parkings.put(parkingId, updatedParking);
            saveDataToCsv();
            return updatedParking;
        }
        return null;
    }
    public void loadDataFromCsv() {
        var parkings = parkingReader.readDataFromCsv(csvFilePath);
        for (var parking : parkings) {
            createParking(parking);
        }
    }

    private void saveDataToCsv() {
        parkingWriter.writeDataToCsv(new LinkedList<>(parkings.values()), csvFilePath);
    }
    
}
