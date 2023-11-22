package com.phoenix.Phoenix.dto.inventories;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertInventoriesDTO {
    @NotBlank(message = "Name tidak boleh kosong.")
    private String name;

    @NotNull(message = "Stock harus diisi.")
    @Min(value = 0, message = "Tidak boleh memilki nilai minus.")
    @Max(value = 9999, message = "Tidak boleh melebihi 9999.")
    private Integer stock;

    @Size(max = 1000, message = "Tidak boleh lebih dari 1000 chars.")
    private String description;
}
