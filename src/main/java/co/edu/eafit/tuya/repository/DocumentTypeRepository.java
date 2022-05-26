package co.edu.eafit.tuya.repository;

import co.edu.eafit.tuya.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Short> {
    Optional<DocumentType> findByDocumentType(String documentType);
}
