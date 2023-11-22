package com.phoenix.Phoenix.dto.account;

import com.phoenix.Phoenix.validation.UniqueUsernameAdministrator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertAdministratorsDTO {
    @UniqueUsernameAdministrator(message = "Username sudah terpakai.")
    @NotBlank(message = "Username tidak boleh kosong")
    private String username;

    private String password;

    @NotBlank(message = "Job Title tidak boleh kosong")
    private String jobTitle;
}
