package co.edu.eafit.tuya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GenericResponseDto {
    private Boolean status;
    private String detail;
    private Object response;
}
