package com.phoenix.Phoenix.dto.rooms;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomsDTO {
    private String number;

    @NotNull(message = "floor harus diisi")
    private Integer floor;

    @NotBlank(message = "Room Type harus diisi")
    private String roomType;

    @NotNull(message = "Guest Limit harus diisi.")
    private Integer guestLimit;

    @NotNull(message = "Cost harus diinput.")
    @Digits(integer = 12, fraction = 2, message = "Harus angka decimal dengan 2 angka dibelakang koma.")
    private Double cost;

    @Size(max = 1000, message = "Tidak boleh lebih dari 1000 chars.")
    private String description;

    private MultipartFile image;
    private String imagePath;

    public UpdateRoomsDTO(String number, Integer floor, String roomType, Integer guestLimit, Double cost, String description, String imagePath) {
        this.number = number;
        this.floor = floor;
        this.roomType = roomType;
        this.guestLimit = guestLimit;
        this.cost = cost;
        this.description = description;
        this.imagePath = imagePath;
    }
}
