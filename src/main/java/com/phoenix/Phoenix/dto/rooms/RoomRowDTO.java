package com.phoenix.Phoenix.dto.rooms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRowDTO {
    private String number;
    private Integer floor;
    private String roomType;
    private Integer guestLimit;
    private Double cost;
    private String imgPath;
    private String status;
    private Boolean isBooked;

    public RoomRowDTO(String number, Integer floor, String roomType, Integer guestLimit, Double cost, String imgPath) {
        this.number = number;
        this.floor = floor;
        this.roomType = roomType;
        this.guestLimit = guestLimit;
        this.cost = cost;
        this.imgPath = imgPath;
    }
}
