package co.edu.eafit.tuya.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    @NotBlank
    private String documentNumber;
    @NotBlank
    private String password;
    @NotBlank
    private Short documentTypeId;
}
