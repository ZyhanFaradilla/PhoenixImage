package com.phoenix.Phoenix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Rooms")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {
    @Id
    @Column(name = "Number")
    private String number;

    @Column(name = "Floor")
    private Integer floor;

    @Column(name = "RoomType")
    private String roomType;

    @Column(name = "GuestLimit")
    private Integer guestLimit;

    @Column(name = "Description")
    private String description;

    @Column(name = "Cost")
    private Double cost;

    @Column(name = "ImgPath")
    private String imgPath;
}
