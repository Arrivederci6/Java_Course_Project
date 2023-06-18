package ua.lviv.iot.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.parking.model.ParkingSpot;
import ua.lviv.iot.parking.business.ParkingSpotService;

import java.util.List;

@RequestMapping("/parkingSpots")
@RestController
public class ParkingSpotController {
    private final ParkingSpotService parkingSpotService;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping
    public List<ParkingSpot> getParkingSpots() {
        return parkingSpotService.getParkingSpots();
    }

    @GetMapping(path = "/{id}")
    public ParkingSpot getParkingSpot(@PathVariable("id") Integer parkingSpotId) {
        return parkingSpotService.getParkingSpot(parkingSpotId);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ParkingSpot createParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        return parkingSpotService.createParkingSpot(parkingSpot);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParkingSpot(@PathVariable("id") Integer parkingSpotId) {
        HttpStatus status = parkingSpotService.deleteParkingSpot(parkingSpotId) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ParkingSpot> updateParkingSpot(@PathVariable("id") Integer parkingSpotId,
                                                         @RequestBody ParkingSpot parkingSpot) {
        ParkingSpot updatedParkingSpot = parkingSpotService.updateParkingSpot(parkingSpotId, parkingSpot);
        return updatedParkingSpot != null ? ResponseEntity.ok(updatedParkingSpot) : ResponseEntity.notFound().build();
    }
}
