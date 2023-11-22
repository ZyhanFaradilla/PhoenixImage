package com.phoenix.Phoenix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "RoomInventories")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInventories {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RoomNumber")
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "RoomNumber", insertable = false, updatable = false)
    private Rooms rooms;

    @Column(name = "InventoryName")
    private String inventoryName;

    @ManyToOne
    @JoinColumn(name = "InventoryName", insertable = false, updatable = false)
    private Inventories inventories;

    @Column(name = "Quantity")
    private Integer quantity;
}
