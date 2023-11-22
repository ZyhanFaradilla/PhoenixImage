package com.phoenix.Phoenix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "Reservations")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservations {
    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "ReservationMethod")
    private String reservationMethod;

    @Column(name = "RoomNumber")
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "RoomNumber", insertable = false, updatable = false)
    private Rooms rooms;

    @Column(name = "GuestUsername")
    private String guestUsername;

    @ManyToOne
    @JoinColumn(name = "GuestUsername", insertable = false, updatable = false)
    private Guests guests;

    @Column(name = "BookDate")
    private LocalDateTime bookDate;

    @Column(name = "CheckIn")
    private LocalDateTime checkIn;

    @Column(name = "CheckOut")
    private LocalDateTime checkOut;

    @Column(name = "Cost")
    private Double cost;

    @Column(name = "PaymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "Remark")
    private String remark;
}
