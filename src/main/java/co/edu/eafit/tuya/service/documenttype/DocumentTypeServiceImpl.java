package co.edu.eafit.tuya.service.documenttype;

import co.edu.eafit.tuya.model.DocumentType;
import co.edu.eafit.tuya.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{
    private DocumentTypeRepository activityRepository;

    @Autowired
    public void setDocumentTypeRepository(DocumentTypeRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Optional<DocumentType> findById(Short id) {
        return this.activityRepository.findById(id);
    }


    @Override
    public List<DocumentType> getList() {
        return this.activityRepository.findAll();
    }


    @Override
    public boolean existById(Short id) {
        return this.activityRepository.existsById(id);
    }

    public Optional<DocumentType> findByDocumentType(String documentType) {
        return this.activityRepository.findByDocumentType(documentType);
    }
}
