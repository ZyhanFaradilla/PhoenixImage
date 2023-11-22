package com.phoenix.Phoenix.dto.inventories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoriesRowDTO {
    private String name;
    private Integer stock;
    private String description;
}
