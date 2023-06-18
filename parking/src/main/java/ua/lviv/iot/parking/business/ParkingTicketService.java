package ua.lviv.iot.parking.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.parking.dataaccess.ParkingTicketRepository;
import ua.lviv.iot.parking.model.ParkingTicket;

import java.util.List;
@Service
public class ParkingTicketService {
    private final ParkingTicketRepository parkingTicketRepository;

    @Autowired
    public ParkingTicketService(ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.parkingTicketRepository.loadDataFromCsv();
    }

    public List<ParkingTicket> getParkingTickets() {
        return parkingTicketRepository.getParkingTickets();
    }

    public ParkingTicket getParkingTicket(Integer parkingTicketId) {
        return parkingTicketRepository.getParkingTicket(parkingTicketId);
    }

    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket) {
        return parkingTicketRepository.createParkingTicket(parkingTicket);
    }

    public boolean deleteParkingTicket(Integer parkingTicketId) {
        return parkingTicketRepository.deleteParkingTicket(parkingTicketId);
    }

    public ParkingTicket updateParkingTicket(Integer parkingTicketId, ParkingTicket updatedParkingTicket) {
        return parkingTicketRepository.updateParkingTicket(parkingTicketId, updatedParkingTicket);
    }
}
