package co.edu.eafit.tuya.repository;

import co.edu.eafit.tuya.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Short> {
}
