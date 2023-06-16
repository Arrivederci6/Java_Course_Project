package ua.lviv.iot.parking.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parking {
    private String address;
    private String marketNetwork;
    private int countOfParkingSpots;
    private Integer id;

    public Parking(String address,String marketNetwork, int countOfParkingSpots ) {
        this.address = address;
        this.marketNetwork = marketNetwork;
        this.countOfParkingSpots = countOfParkingSpots;

    }
}
