package com.phoenix.Phoenix.dto.rooms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationDTO {
    private String number;
    private String username;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double costTotal;
    private String reservationMethod;
    private String paymentMethod;
    private String remark;
}
