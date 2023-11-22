package com.phoenix.Phoenix.dto.roomInventories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInventoriesRowDTO {
    private Long id;
    private String roomNumber;
    private String inventoryName;
    private Integer stock;
    private Integer quantity;
}
