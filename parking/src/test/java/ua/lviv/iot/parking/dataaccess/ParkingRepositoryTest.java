package ua.lviv.iot.parking.dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parking.business.ParkingService;
import ua.lviv.iot.parking.model.Parking;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingRepositoryTest {
    private ParkingRepository parkingRepository;
    private ParkingService parkingService;
    private List<Parking> expectedParkings = new ArrayList<>(List.of(
            new Parking("street1", "lpbu", 50),
            new Parking("street2", "lnu", 70)));
    @BeforeEach
    public void setup() {
        parkingRepository = new ParkingRepository();
        for (var parking: expectedParkings) {
            parkingRepository.createParking(parking);
        }
        parkingRepository.loadDataFromCsv();

        parkingService = new ParkingService(parkingRepository);
    }
    @Test
    public void testCreateParking() {
        Parking newParking = new Parking("Stepana Bandery", "LPNU",  50);
        Parking createdParking = parkingService.createParking(newParking);

        assertEquals(newParking, createdParking);

        List<Parking> parkings = parkingRepository.getParkings();
        assertTrue(parkings.contains(newParking));
    }

    @Test
    public void testDeleteParking() {
        var parkingId = 1;
        boolean isDeleted = parkingService.deleteParking(parkingId);

        assertTrue(isDeleted);

        List<Parking> parkings = parkingRepository.getParkings();
        for (Parking parking : parkings) {
            assertNotEquals(parkingId, parking.getId());
        }
    }

    @Test
    public void testUpdateParking() {
        var parkingId = 1;
        Parking updatedParking = new Parking("Stepana Bandery", "LPNU",  50);
        Parking modifiedParking = parkingService.updateParking(parkingId, updatedParking);

        assertEquals(updatedParking, modifiedParking);

        Parking retrievedParking = parkingRepository.getParking(parkingId);
        assertEquals(updatedParking, retrievedParking);
    }
}
