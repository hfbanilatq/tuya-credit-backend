package co.edu.eafit.tuya.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    @NotBlank
    @NotNull
    private String documentNumber;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @NotNull
    private Short documentTypeId;
}
