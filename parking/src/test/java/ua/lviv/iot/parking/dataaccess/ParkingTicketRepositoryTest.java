package ua.lviv.iot.parking.dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.parking.business.ParkingTicketService;
import ua.lviv.iot.parking.model.ParkingTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingTicketRepositoryTest {
    private ParkingTicketRepository parkingTicketRepository;
    private ParkingTicketService parkingTicketService;
    private List<ParkingTicket> expectedParkingTickets = new ArrayList<>(List.of(
            new ParkingTicket(1, "AB1234BA",
                    LocalDateTime.of(2023, 6, 17, 13, 0) ,
                    LocalDateTime.of(2023, 6, 17, 18, 0)),

            new ParkingTicket(2, "AB4321BA",
                    LocalDateTime.of(2023, 6, 18, 10, 0) ,
                    LocalDateTime.of(2023, 6, 18, 15, 0))));
    @BeforeEach
    public void setup() {
        parkingTicketRepository = new ParkingTicketRepository();
        for (var parkingTicket: expectedParkingTickets) {
            parkingTicketRepository.createParkingTicket(parkingTicket);
        }
        parkingTicketRepository.loadDataFromCsv();

        parkingTicketService = new ParkingTicketService(parkingTicketRepository);
    }

    @Test
    public void testGetParkingTicket() {
        Integer parkingTicketId = 1;
        ParkingTicket actualParkingTicket = parkingTicketService.getParkingTicket(parkingTicketId);

        assertEquals(expectedParkingTickets.get(0), actualParkingTicket);
    }

    @Test
    public void testCreateParkingTicket() {
        var spotNumber = 1;
        var carNumber = "AB1234BA";
        var startTime = LocalDateTime.of(2023, 6, 17, 13, 0);
        var endTime = LocalDateTime.of(2023, 6, 17, 18, 0);

        ParkingTicket expectedParkingTicket = new ParkingTicket(spotNumber, carNumber, startTime, endTime);
        ParkingTicket actualParkingTicket = parkingTicketService.createParkingTicket(expectedParkingTicket);

        assertEquals(expectedParkingTicket, actualParkingTicket);

        List<ParkingTicket> parkingTickets = parkingTicketRepository.getParkingTickets();
        assertTrue(parkingTickets.contains(expectedParkingTicket));
    }

    @Test
    public void testDeleteParkingTicket() {
        Integer parkingTicketId = 1;
        boolean isDeleted = parkingTicketService.deleteParkingTicket(parkingTicketId);

        assertTrue(isDeleted);

        List<ParkingTicket> parkingTickets = parkingTicketRepository.getParkingTickets();
        for (ParkingTicket parkingTicket : parkingTickets) {
            assertNotEquals(parkingTicketId, parkingTicket.getId());
        }
    }

    @Test
    public void testUpdateParkingTicket() {
        Integer parkingTicketId = 1;
        var spotNumber = 1;
        var carNumber = "AB1234BA";
        var startTime = LocalDateTime.of(2023, 6, 17, 13, 0);
        var endTime = LocalDateTime.of(2023, 6, 17, 18, 0);
        ParkingTicket updatedParkingTicket = new ParkingTicket(spotNumber, carNumber, startTime, endTime);
        ParkingTicket modifiedParkingTicket = parkingTicketService.updateParkingTicket(parkingTicketId, updatedParkingTicket);

        assertEquals(updatedParkingTicket, modifiedParkingTicket);

        ParkingTicket retrievedParkingTicket = parkingTicketRepository.getParkingTicket(parkingTicketId);
        assertEquals(updatedParkingTicket, retrievedParkingTicket);
    }
}
