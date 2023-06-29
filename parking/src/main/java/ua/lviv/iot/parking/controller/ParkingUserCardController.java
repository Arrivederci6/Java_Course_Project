package ua.lviv.iot.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.parking.business.ParkingUserCardService;
import ua.lviv.iot.parking.model.ParkingUserCard;

import java.util.List;

@RequestMapping("/parkingUserCards")
@RestController
public class ParkingUserCardController {
    private final ParkingUserCardService parkingUserCardService;

    @Autowired
    public ParkingUserCardController(ParkingUserCardService parkingUserCardService) {
        this.parkingUserCardService = parkingUserCardService;
    }

    @GetMapping
    public List<ParkingUserCard> getParkingUserCards() {
        return parkingUserCardService.getParkingUserCards();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ParkingUserCard> getParkingUserCard(@PathVariable("id") Integer parkingUserCardId) {
        ParkingUserCard parkingUserCard = parkingUserCardService.getParkingUserCard(parkingUserCardId);
        if (parkingUserCard != null) {
            return ResponseEntity.ok(parkingUserCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ParkingUserCard createParkingUserCard(@RequestBody ParkingUserCard parkingUserCard) {
        return parkingUserCardService.createParkingUserCard(parkingUserCard);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParkingUserCard(@PathVariable("id") Integer parkingUserCardId) {
        HttpStatus status = parkingUserCardService.deleteParkingUserCard(parkingUserCardId) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ParkingUserCard> updateParkingUserCard(@PathVariable("id") Integer parkingUserCardId,
                                                             @RequestBody ParkingUserCard parkingUserCard) {
        ParkingUserCard updatedParkingUserCard = parkingUserCardService.updateParkingUserCard(parkingUserCardId, parkingUserCard);
        return updatedParkingUserCard != null ? ResponseEntity.ok(updatedParkingUserCard) : ResponseEntity.notFound().build();
    }
}
