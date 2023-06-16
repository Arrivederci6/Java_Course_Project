package ua.lviv.iot.parking.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ParkingTicket {
    private int spotNumber;
    private String carNumber;
    private Integer id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(int spotNumber, String carNumber, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.spotNumber = spotNumber;
        this.carNumber = carNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }
}
