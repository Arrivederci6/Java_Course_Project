package ua.lviv.iot.parking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.parking.model.ParkingSpot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/parkingSpots")
@RestController
public class ParkingSpotController {
    private Map<Integer, ParkingSpot> parkingSpots = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();

    @GetMapping
    public List<ParkingSpot> getParkingSpots() {
        return new LinkedList<ParkingSpot>(parkingSpots.values());
    }

    @GetMapping(path = "/{id}")
    public ParkingSpot getParkingSpot(final @PathVariable("id") Integer parkingSpotId) {
        return parkingSpots.get(parkingSpotId);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ParkingSpot createParkingSpot(final @RequestBody ParkingSpot parkingSpot) {
        parkingSpot.setId(idCounter.incrementAndGet());
        parkingSpots.put(parkingSpot.getId(), parkingSpot);
        return parkingSpot;
//        String csvData = parkingSpot.getSpotNumber() + ", " + parkingSpot.getCarNumber() + ", " + parkingSpot.getId();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//
//        return csvData;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ParkingSpot> deleteParkingSpot(@PathVariable("id") Integer parkingSpotId) {
        HttpStatus status = parkingSpots.remove(parkingSpotId) == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ParkingSpot> updateParkingSpot(final @PathVariable("id") Integer parkingSpotId,
                                                         final @RequestBody ParkingSpot parkingSpot) {

        if (parkingSpots.containsKey(parkingSpotId)) {
            parkingSpot.setId(parkingSpotId);
            parkingSpots.put(parkingSpot.getId(), parkingSpot);
            return ResponseEntity.ok(parkingSpot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

