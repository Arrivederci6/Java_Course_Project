package ua.lviv.iot.parking.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parking.model.Parking;
import ua.lviv.iot.parking.dataaccess.ParkingRepository;

import java.util.List;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    @Autowired
    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
        this.parkingRepository.loadDataFromCsv();
    }

    public List<Parking> getParkings() {
        return parkingRepository.getParkings();
    }

    public Parking getParking(Integer parkingId) {
        return parkingRepository.getParking(parkingId);
    }

    public Parking createParking(Parking parking) {
        return parkingRepository.createParking(parking);
    }

    public boolean deleteParking(Integer parkingId) {
        return parkingRepository.deleteParking(parkingId);
    }

    public Parking updateParking(Integer parkingId, Parking updatedParking) {
        return parkingRepository.updateParking(parkingId, updatedParking);
    }
}
