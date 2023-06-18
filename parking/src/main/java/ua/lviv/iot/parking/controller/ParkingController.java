package ua.lviv.iot.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.parking.model.Parking;
import ua.lviv.iot.parking.business.ParkingService;

import java.util.List;

@RequestMapping("/parkings")
@RestController
public class ParkingController {
    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<Parking> getParkings() {
        return parkingService.getParkings();
    }

    @GetMapping(path = "/{id}")
    public Parking getParking(@PathVariable("id") Integer parkingId) {
        return parkingService.getParking(parkingId);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Parking createParking(@RequestBody Parking parking) {
        return parkingService.createParking(parking);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParking(@PathVariable("id") Integer parkingId) {
        HttpStatus status = parkingService.deleteParking(parkingId) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Parking> updateParking(@PathVariable("id") Integer parkingId,
                                                         @RequestBody Parking parking) {
        Parking updatedParking = parkingService.updateParking(parkingId, parking);
        return updatedParking != null ? ResponseEntity.ok(updatedParking) : ResponseEntity.notFound().build();
    }
}
