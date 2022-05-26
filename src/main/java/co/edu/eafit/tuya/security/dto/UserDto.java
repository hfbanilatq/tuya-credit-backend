package co.edu.eafit.tuya.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String documentNumber;
    private String email;
    private Set<String> roles = new HashSet<>();
}
