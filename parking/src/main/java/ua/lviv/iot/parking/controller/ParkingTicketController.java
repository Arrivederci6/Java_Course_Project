package ua.lviv.iot.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.parking.business.ParkingTicketService;
import ua.lviv.iot.parking.model.ParkingTicket;

import java.util.List;

@RequestMapping("/parkingTickets")
@RestController
public class ParkingTicketController {
    private final ParkingTicketService parkingTicketService;

    @Autowired
    public ParkingTicketController(ParkingTicketService parkingTicketService) {
        this.parkingTicketService = parkingTicketService;
    }

    @GetMapping
    public List<ParkingTicket> getParkingTickets() {
        return parkingTicketService.getParkingTickets();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ParkingTicket> getParkingTicket(@PathVariable("id") Integer parkingTicketId) {
        ParkingTicket parkingTicket = parkingTicketService.getParkingTicket(parkingTicketId);
        if (parkingTicket != null) {
            return ResponseEntity.ok(parkingTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ParkingTicket createParkingTicket(@RequestBody ParkingTicket parkingTicket) {
        return parkingTicketService.createParkingTicket(parkingTicket);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParkingTicket(@PathVariable("id") Integer parkingTicketId) {
        HttpStatus status = parkingTicketService.deleteParkingTicket(parkingTicketId) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ParkingTicket> updateParkingTicket(@PathVariable("id") Integer parkingTicketId,
                                                         @RequestBody ParkingTicket parkingTicket) {
        ParkingTicket updatedParkingTicket = parkingTicketService.updateParkingTicket(parkingTicketId, parkingTicket);
        return updatedParkingTicket != null ? ResponseEntity.ok(updatedParkingTicket) : ResponseEntity.notFound().build();
    }
}
