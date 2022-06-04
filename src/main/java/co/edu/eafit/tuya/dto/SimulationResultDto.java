package co.edu.eafit.tuya.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class SimulationResultDto {
    private BigDecimal realPrice;
    private BigDecimal promotionPrice;
    private BigDecimal pricePaid;

    private BigDecimal priceWithCreditCard;
    @Singular
    List<MonthlyFeeDto> monthlyFees;
}
