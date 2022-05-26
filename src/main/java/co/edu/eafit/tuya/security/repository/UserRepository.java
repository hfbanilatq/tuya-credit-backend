package co.edu.eafit.tuya.security.repository;

import co.edu.eafit.tuya.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
    Optional<User> findByDocumentNumber(String documentNumber);
    Optional<User> findByDocumentType_IdAndDocumentNumber(Short dyId, String documentNumber);

    boolean existsByDocumentType_IdAndDocumentNumber(Short id,String documentNumber);
}
