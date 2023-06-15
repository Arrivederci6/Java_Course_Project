package ua.lviv.iot.parking.model;

import lombok.*;

@Data
@NoArgsConstructor
public class ParkingSpot {
    private int spotNumber;
    private String carNumber;
    private Integer id;

    public ParkingSpot(int spotNumber, String carNumber) {
        this.spotNumber = spotNumber;
        this.carNumber = carNumber;
    }
}
