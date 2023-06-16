package ua.lviv.iot.parking.dataaccess;

import org.springframework.stereotype.Repository;
import ua.lviv.iot.parking.model.ParkingTicket;
import ua.lviv.iot.parking.reader.ParkingTicketReader;
import ua.lviv.iot.parking.writer.ParkingTicketWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ParkingTicketRepository {
    private Map<Integer, ParkingTicket> parkingTickets = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();
    private final String csvFilePath = "parkingTicket-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".csv";


    public List<ParkingTicket> getParkingTickets() {
        return new LinkedList<>(parkingTickets.values());
    }

    public ParkingTicket getParkingTicket(Integer parkingTicketId) {
        return parkingTickets.get(parkingTicketId);
    }

    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket) {
        parkingTicket.setId(idCounter.incrementAndGet());
        parkingTickets.put(parkingTicket.getId(), parkingTicket);
        saveDataToCsv();
        return parkingTicket;
    }

    public boolean deleteParkingTicket(Integer parkingTicketId) {
        boolean removed = parkingTickets.remove(parkingTicketId) != null;
        if (removed) {
            saveDataToCsv();
        }
        return removed;
    }

    public ParkingTicket updateParkingTicket(Integer parkingTicketId, ParkingTicket updatedParkingTicket) {
        if (parkingTickets.containsKey(parkingTicketId)) {
            updatedParkingTicket.setId(parkingTicketId);
            parkingTickets.put(parkingTicketId, updatedParkingTicket);
            saveDataToCsv();
            return updatedParkingTicket;
        }
        return null;
    }
    public void loadDataFromCsv() {
        List<ParkingTicket> parkingTickets = ParkingTicketReader.readDataFromCsv(csvFilePath);
        for (ParkingTicket parkingTicket : parkingTickets) {
            createParkingTicket(parkingTicket);
        }
    }

    private void saveDataToCsv() {
        ParkingTicketWriter.writeDataToCsv(new LinkedList<>(parkingTickets.values()), csvFilePath);
    }
}
