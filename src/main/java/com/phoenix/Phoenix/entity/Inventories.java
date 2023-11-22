package com.phoenix.Phoenix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Inventories")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventories {
    @Id
    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Stock")
    private Integer stock;
}
