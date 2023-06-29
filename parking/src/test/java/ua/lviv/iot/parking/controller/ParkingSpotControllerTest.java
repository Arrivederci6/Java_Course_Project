package ua.lviv.iot.parking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parking.business.ParkingSpotService;
import ua.lviv.iot.parking.dataaccess.ParkingSpotRepository;
import ua.lviv.iot.parking.model.ParkingSpot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingSpotControllerTest {
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingSpotService parkingSpotService;
    private List<ParkingSpot> expectedParkingSpots = new ArrayList<>(List.of(
            new ParkingSpot(1, "AB1234BA",
                    LocalDateTime.of(2023, 6, 17, 13, 0) ,
                    LocalDateTime.of(2023, 6, 17, 18, 0)),

            new ParkingSpot(2, "AB4321BA",
                    LocalDateTime.of(2023, 6, 18, 10, 0) ,
                    LocalDateTime.of(2023, 6, 18, 15, 0))));


    @BeforeEach
    public void setup() {
        parkingSpotRepository = new ParkingSpotRepository();
        for (var parkingSpot: expectedParkingSpots) {
            parkingSpotRepository.createParkingSpot(parkingSpot);
        }
        parkingSpotRepository.loadDataFromCsv();

        parkingSpotService = new ParkingSpotService(parkingSpotRepository);

    }

    @Test
    public void testCreateParkingSpot() {
        var spotNumber = 1;
        var carNumber = "AB1234BA";
        var startTime = LocalDateTime.of(2023, 6, 17, 13, 0);
        var endTime = LocalDateTime.of(2023, 6, 17, 18, 0);

        ParkingSpot newParkingSpot = new ParkingSpot(spotNumber, carNumber, startTime, endTime);
        expectedParkingSpots.add(newParkingSpot);
        ParkingSpot createdParkingSpot = parkingSpotService.createParkingSpot(newParkingSpot);

        assertEquals(newParkingSpot, createdParkingSpot);

        List<ParkingSpot> parkingSpots = parkingSpotRepository.getParkingSpots();
        assertTrue(parkingSpots.contains(newParkingSpot));
    }

    @Test
    public void testDeleteParkingSpot() {
        var spotNumber = 1;
        boolean isDeleted = parkingSpotService.deleteParkingSpot(spotNumber);

        assertTrue(isDeleted);

        List<ParkingSpot> parkingSpots = parkingSpotRepository.getParkingSpots();
        for (ParkingSpot parkingSpot : parkingSpots) {
            assertNotEquals(spotNumber, parkingSpot.getId());
        }
    }

    @Test
    public void testUpdateParkingSpot() {
        var spotNumber = 1;
        var carNumber = "AB1234BA";
        var startTime = LocalDateTime.of(2023, 6, 17, 13, 0);
        var endTime = LocalDateTime.of(2023, 6, 17, 18, 0);

        var updatedParkingSpot = new ParkingSpot(spotNumber, carNumber, startTime, endTime);
        var modifiedParkingSpot = parkingSpotService.updateParkingSpot(spotNumber, updatedParkingSpot);

        assertEquals(updatedParkingSpot, modifiedParkingSpot);

        ParkingSpot retrievedParkingSpot = parkingSpotRepository.getParkingSpot(spotNumber);
        assertEquals(updatedParkingSpot, retrievedParkingSpot);
    }
}
