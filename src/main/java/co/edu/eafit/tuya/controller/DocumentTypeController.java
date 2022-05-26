package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.model.DocumentType;
import co.edu.eafit.tuya.service.documenttype.DocumentTypeService;
import co.edu.eafit.tuya.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-type")
@CrossOrigin("*")
public class DocumentTypeController {
    private DocumentTypeService documentTypeService;

    @Autowired
    public void setDocumentTypeService(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }


    @GetMapping("/list")
    public ResponseEntity<?> getList() {
            return new ResponseEntity<>(this.documentTypeService.getList(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable Short id) {
            return new ResponseEntity<>(this.documentTypeService.findById(id).get(), HttpStatus.OK);
    }
}
