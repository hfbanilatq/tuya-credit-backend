package co.edu.eafit.tuya.model;

import co.edu.eafit.tuya.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfInstallments;

    @ManyToMany
    @JoinTable(name = "product_has_simulation", joinColumns = @JoinColumn(name = "simulationId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "creditCardId", referencedColumnName = "id")
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
