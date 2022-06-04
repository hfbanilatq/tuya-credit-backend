package co.edu.eafit.tuya.dto;

import co.edu.eafit.tuya.model.CreditCard;
import co.edu.eafit.tuya.model.Product;
import co.edu.eafit.tuya.security.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class SimulationDto {
    private Long id;
    private int numberOfInstallments;
    @Singular
    private Set<ProductDto> products;
    private Short creditCardId;
    private int userId;
    private Date createdAt;
    private String creditCardName;
}
