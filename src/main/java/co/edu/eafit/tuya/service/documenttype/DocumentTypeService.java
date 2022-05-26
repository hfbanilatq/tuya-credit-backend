package co.edu.eafit.tuya.service.documenttype;

import co.edu.eafit.tuya.model.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {
    Optional<DocumentType> findById(Short id);
    List<DocumentType> getList();
    boolean existById(Short id);
}
