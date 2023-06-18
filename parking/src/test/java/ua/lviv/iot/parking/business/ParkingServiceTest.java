package ua.lviv.iot.parking.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parking.dataaccess.ParkingRepository;
import ua.lviv.iot.parking.model.Parking;
import ua.lviv.iot.parking.model.ParkingSpot;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingServiceTest {
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
    public void testGetParking() {
        var parkingId = 1;
        Parking actualParking = parkingService.getParking(parkingId);

        assertEquals(expectedParkings.get(0), actualParking);
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