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
public class CreditCardDto {
    private Short id;
    private String type;
    private BigDecimal feeValue;
    private Integer maxFee;
    private Double monthlyInterest;
    private Double effectiveAnnualInterest;
    private String imageUrl;
}
