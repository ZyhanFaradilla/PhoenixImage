package com.phoenix.Phoenix.dto.guest;

import com.phoenix.Phoenix.validation.ComparePassword;
import com.phoenix.Phoenix.validation.UniqueUsernameGuest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ComparePassword(message = "Password tidak sesuai.")
public class RegisterGuestDTO {
    @UniqueUsernameGuest(message = "Username ini sudah terpakai.")
    @NotBlank(message = "Username tidak boleh kosong.")
    private String username;

    @NotBlank(message = "Password harus diisi.")
    private String password;

    @NotBlank(message = "Password harus dikonfirmasi.")
    private String confirmPassword;

    @NotBlank(message = "First Name harus diisi.")
    private String firstName;

    @NotBlank(message = "Middle Name harus diisi.")
    private String middleName;

    @NotBlank(message = "Last Name harus diisi.")
    private String lastName;

    @NotNull(message="Birth Date harus diisi.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Gender harus diisi.")
    private String gender;

    @NotBlank(message = "Citizenship harus diisi.")
    private String citizenship;

    @NotBlank(message = "Id Number harus diisi.")
    private String idNumber;
}
