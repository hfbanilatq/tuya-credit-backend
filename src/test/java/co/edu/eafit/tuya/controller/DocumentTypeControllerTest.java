package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.model.DocumentType;
import co.edu.eafit.tuya.service.documenttype.DocumentTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.when;
@SpringBootTest
class DocumentTypeControllerTest {

    @Mock
    private DocumentTypeService documentTypeService;
    @InjectMocks
    private DocumentTypeController documentTypeController;

    @Test
    void whenGetListCorrectly_thenShouldReturnStatusOk() {

        List<DocumentType> documentTypes = new ArrayList<>();
        documentTypes.add(new DocumentType("Cedula de ciudadanía"));
        when(documentTypeService.getList()).thenReturn(documentTypes);

        ResponseEntity<?> response = this.documentTypeController.getList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentTypes, response.getBody());
    }

    @Test
    void whenGetById_thenShouldReturnStatusOk() {
        DocumentType documentType =new DocumentType("Cedula de ciudadanía");
        when(documentTypeService.findById(anyShort())).thenReturn(java.util.Optional.of(documentType));

        ResponseEntity<?> response = this.documentTypeController.getById((short)1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentType, response.getBody());
    }
}