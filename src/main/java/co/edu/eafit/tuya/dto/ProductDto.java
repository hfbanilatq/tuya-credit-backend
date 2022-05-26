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
public class ProductDto {
    private Long id;
    private String reference;
    private String imageUrl;
    private String description;
    private BigDecimal price;
    private String warehouse;
    private Double discount;
    private Double discountWithCreditCard;
    private BigDecimal priceWithAllPayments;
    private BigDecimal priceWithCreditCard;
}
