package co.edu.eafit.tuya.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtDto {
    private String token;

    public JwtDto(String token) {
        this.token = token;
    }
}
