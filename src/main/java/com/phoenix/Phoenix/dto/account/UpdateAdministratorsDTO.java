package com.phoenix.Phoenix.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdministratorsDTO {
    private String username;

    private String password;

    @NotBlank(message = "Job Title tidak boleh kosong")
    private String jobTitle;
}
