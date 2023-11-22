package com.phoenix.Phoenix.dto.roomServices;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRoomServicesDTO {
    @NotBlank(message = "Employee Number harus diisi.")
    private String employeeNumber;

    @NotBlank(message = "First Name harus diisi.")
    private String firstName;
    private String middleName;
    private String lastName;

    @NotBlank(message = "Outsourcing Company harus diisi.")
    private String outsourcingCompany;
}
