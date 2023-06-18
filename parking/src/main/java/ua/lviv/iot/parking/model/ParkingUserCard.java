package ua.lviv.iot.parking.model;

import lombok.*;
@NoArgsConstructor
@Data
public class ParkingUserCard {
    private String firstName;
    private String lastName;
    private int age;
    private int userCodeId;
    private Integer id;

    public ParkingUserCard(String firstName, String lastName, int age, int userCodeId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.userCodeId = userCodeId;
    }
}
