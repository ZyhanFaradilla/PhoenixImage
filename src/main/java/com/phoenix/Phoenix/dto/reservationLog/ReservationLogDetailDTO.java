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
public class ReservationLogDetailDTO {
    private String code;
    private String reservationMethod;
    private String roomNumber;
    private Integer roomFloor;
    private String roomType;
    private String guestUsername;
    private String guestFullName;
    private LocalDateTime bookDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double cost;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String remark;
}
