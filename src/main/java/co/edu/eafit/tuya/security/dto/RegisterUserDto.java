package co.edu.eafit.tuya.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RegisterUserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String documentNumber;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
    @NotBlank
    private Short documentType;
}
