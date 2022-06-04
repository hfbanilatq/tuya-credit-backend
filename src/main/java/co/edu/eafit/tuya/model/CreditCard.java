package co.edu.eafit.tuya.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String type;
    private BigDecimal feeValue;
    private Integer maxFee;
    private Double monthlyInterest;
    private Double effectiveAnnualInterest;
    private String imageUrl;
}
