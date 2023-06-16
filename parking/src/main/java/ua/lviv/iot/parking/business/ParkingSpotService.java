package ua.lviv.iot.parking.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parking.model.ParkingSpot;
import ua.lviv.iot.parking.dataaccess.ParkingSpotRepository;
import ua.lviv.iot.parking.reader.ParkingSpotReader;
import ua.lviv.iot.parking.writer.ParkingSpotWriter;

import java.util.List;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final String csvFilePath = "parking_data.csv";

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        loadDataFromCsv();
    }

    private void loadDataFromCsv() {
        List<ParkingSpot> parkingSpots = ParkingSpotReader.readDataFromCsv(csvFilePath);
        for (ParkingSpot parkingSpot : parkingSpots) {
            parkingSpotRepository.createParkingSpot(parkingSpot);
        }
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpotRepository.getParkingSpots();
    }

    public ParkingSpot getParkingSpot(Integer parkingSpotId) {
        return parkingSpotRepository.getParkingSpot(parkingSpotId);
    }

    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        ParkingSpot createdParkingSpot = parkingSpotRepository.createParkingSpot(parkingSpot);
        saveDataToCsv();
        return createdParkingSpot;
    }

    public boolean deleteParkingSpot(Integer parkingSpotId) {
        boolean isDeleted = parkingSpotRepository.deleteParkingSpot(parkingSpotId);
        saveDataToCsv();
        return isDeleted;
    }

    public ParkingSpot updateParkingSpot(Integer parkingSpotId, ParkingSpot updatedParkingSpot) {
        ParkingSpot updatedSpot = parkingSpotRepository.updateParkingSpot(parkingSpotId, updatedParkingSpot);
        saveDataToCsv();
        return updatedSpot;
    }

    private void saveDataToCsv() {
        List<ParkingSpot> parkingSpots = parkingSpotRepository.getParkingSpots();
        ParkingSpotWriter.writeDataToCsv(parkingSpots, csvFilePath);
    }

}
