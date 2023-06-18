package ua.lviv.iot.parking.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parking.dataaccess.ParkingUserCardRepository;
import ua.lviv.iot.parking.model.ParkingUserCard;

import java.util.List;

@Service
public class ParkingUserCardService {
    private final ParkingUserCardRepository parkingUserCardRepository;

    @Autowired
    public ParkingUserCardService(ParkingUserCardRepository parkingUserCardRepository) {
        this.parkingUserCardRepository = parkingUserCardRepository;
        this.parkingUserCardRepository.loadDataFromCsv();
    }

    public List<ParkingUserCard> getParkingUserCards() {
        return parkingUserCardRepository.getParkingUserCards();
    }

    public ParkingUserCard getParkingUserCard(Integer parkingUserCardId) {
        return parkingUserCardRepository.getParkingUserCard(parkingUserCardId);
    }

    public ParkingUserCard createParkingUserCard(ParkingUserCard parkingUserCard) {
        return parkingUserCardRepository.createParkingUserCard(parkingUserCard);
    }

    public boolean deleteParkingUserCard(Integer parkingUserCardId) {
        return parkingUserCardRepository.deleteParkingUserCard(parkingUserCardId);
    }

    public ParkingUserCard updateParkingUserCard(Integer parkingUserCardId, ParkingUserCard updatedParkingUserCard) {
        return parkingUserCardRepository.updateParkingUserCard(parkingUserCardId, updatedParkingUserCard);
    }
}
