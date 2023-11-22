package com.phoenix.Phoenix.dto.reservationLog;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationLogRowDTO {
    private String code;
    private String roomNumber;
    private String guestUsername;
    private LocalDateTime bookDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private LocalDateTime paymentDate;
}
