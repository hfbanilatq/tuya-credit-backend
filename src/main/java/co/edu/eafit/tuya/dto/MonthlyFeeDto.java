package co.edu.eafit.tuya.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class MonthlyFeeDto {
    private BigDecimal feeValue;
    private BigDecimal balance;
    private int feeNumber;
}
