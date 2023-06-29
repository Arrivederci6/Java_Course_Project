package ua.lviv.iot.parking.dataaccess;

import org.springframework.stereotype.Repository;
import ua.lviv.iot.parking.model.ParkingSpot;
import ua.lviv.iot.parking.reader.ParkingSpotReader;
import ua.lviv.iot.parking.writer.ParkingSpotWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ParkingSpotRepository {
    private Map<Integer, ParkingSpot> parkingSpots = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();
    private final String csvFilePath = "parkingSpot-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".csv";
    private ParkingSpotWriter parkingSpotWriter = new ParkingSpotWriter();
    private ParkingSpotReader parkingSpotReader = new ParkingSpotReader();


    public List<ParkingSpot> getParkingSpots() {
        return new LinkedList<>(parkingSpots.values());
    }

    public ParkingSpot getParkingSpot(Integer parkingSpotId) {
        return parkingSpots.get(parkingSpotId);
    }

    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.setId(idCounter.incrementAndGet());
        parkingSpots.put(parkingSpot.getId(), parkingSpot);
        saveDataToCsv();
        return parkingSpot;
    }

    public boolean deleteParkingSpot(Integer parkingSpotId) {
        boolean removed = parkingSpots.remove(parkingSpotId) != null;
        if (removed) {
            saveDataToCsv();
        }
        return removed;
    }

    public ParkingSpot updateParkingSpot(Integer parkingSpotId, ParkingSpot updatedParkingSpot) {
        if (parkingSpots.containsKey(parkingSpotId)) {
            updatedParkingSpot.setId(parkingSpotId);
            parkingSpots.put(parkingSpotId, updatedParkingSpot);
            saveDataToCsv();
            return updatedParkingSpot;
        }
        return null;
    }
    public void loadDataFromCsv() {
        List<ParkingSpot> parkingSpots = parkingSpotReader.readDataFromCsv(csvFilePath);
        for (ParkingSpot parkingSpot : parkingSpots) {
            createParkingSpot(parkingSpot);
        }
    }

    private void saveDataToCsv() {
        parkingSpotWriter.writeDataToCsv(new LinkedList<>(parkingSpots.values()), csvFilePath);
    }

}
