package com.phoenix.Phoenix.dto.guest;

import com.phoenix.Phoenix.validation.CheckAuthenticationPasswordGuest;
import com.phoenix.Phoenix.validation.ComparePassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ComparePassword(message = "Password tidak sesuai.")
@CheckAuthenticationPasswordGuest(message = "Old Password Salah.")
public class ChangePasswordGuestDTO {
    private String username;

    @NotBlank(message = "Old Password Harus diisi.")
    @Size(max = 20, message = "Tidak boleh melebihi 20 chars.")
    private String oldPassword;

    @NotBlank(message = "Password Harus diisi.")
    @Size(max = 20, message = "Tidak boleh melebihi 20 chars.")
    private String password;

    @NotBlank(message = "Password Harus di Confirm.")
    @Size(max = 20, message = "Tidak boleh melebihi 20 chars.")
    private String confirmPassword;
}
