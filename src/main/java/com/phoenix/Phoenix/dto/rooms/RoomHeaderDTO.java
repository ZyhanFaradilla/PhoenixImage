package com.phoenix.Phoenix.dto.rooms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomHeaderDTO {
    private String number;
    private String username;
    private Integer floor;
    private String roomType;
    private Integer guestLimit;

    public RoomHeaderDTO(String number, Integer floor, String roomType, Integer guestLimit) {
        this.number = number;
        this.floor = floor;
        this.roomType = roomType;
        this.guestLimit = guestLimit;
    }
}
