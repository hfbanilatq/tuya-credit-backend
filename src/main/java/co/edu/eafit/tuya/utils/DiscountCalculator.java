package co.edu.eafit.tuya.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountCalculator {
    public BigDecimal calculatePriceWithDiscount(BigDecimal price, double discount) {
        if (discount > 0) {
            BigDecimal discountBigDecimal = BigDecimal.valueOf(discount / 100);
            BigDecimal discountValue = price.multiply(discountBigDecimal);
            return price.subtract(discountValue);
        } else {
            return price;
        }
    }
}
