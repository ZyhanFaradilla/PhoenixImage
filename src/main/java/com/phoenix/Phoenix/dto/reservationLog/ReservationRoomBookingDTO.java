package com.phoenix.Phoenix.dto.reservationLog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRoomBookingDTO {
    private String number;
    private String username;
    private Integer floor;
    private String roomType;
    private Integer guestLimit;
    private Double costPerDay;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double costTotal;
    private LocalDateTime paymentDate;
    private String reservationMethod;
    private String paymentMethod;
    private String remark;
}
