package ua.lviv.iot.parking.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parking.model.ParkingSpot;
import ua.lviv.iot.parking.dataaccess.ParkingSpotRepository;

import java.util.List;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingSpotRepository.loadDataFromCsv();
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpotRepository.getParkingSpots();
    }

    public ParkingSpot getParkingSpot(Integer parkingSpotId) {
        return parkingSpotRepository.getParkingSpot(parkingSpotId);
    }

    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.createParkingSpot(parkingSpot);
    }

    public boolean deleteParkingSpot(Integer parkingSpotId) {
        return parkingSpotRepository.deleteParkingSpot(parkingSpotId);
    }

    public ParkingSpot updateParkingSpot(Integer parkingSpotId, ParkingSpot updatedParkingSpot) {
        return parkingSpotRepository.updateParkingSpot(parkingSpotId, updatedParkingSpot);
    }
}
