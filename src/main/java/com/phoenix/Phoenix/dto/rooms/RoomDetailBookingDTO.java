package com.phoenix.Phoenix.dto.rooms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailBookingDTO {
    private String number;
    private Integer floor;
    private String roomType;
    private Integer guestLimit;
    private Double cost;
    private String description;
}
