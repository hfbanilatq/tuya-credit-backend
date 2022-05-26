package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.dto.ProductDto;
import co.edu.eafit.tuya.dto.ProductPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @Test
    void getProductsPage() {
        ResponseEntity<?> responseEntity = this.productController.getProductsPage(null, 0, 5);
        GenericResponseDto genericResponseDto = (GenericResponseDto) responseEntity.getBody();
        assert genericResponseDto != null;
        ProductPageDto productPageDto = (ProductPageDto) genericResponseDto.getResponse();

        assertTrue(genericResponseDto.getStatus());
        assertEquals(5,productPageDto.getProducts().size() );
    }

    @Test
    void getProducts() {
        ResponseEntity<?> responseEntity = this.productController.getProducts();
        GenericResponseDto genericResponseDto = (GenericResponseDto) responseEntity.getBody();
        assert genericResponseDto != null;
        List<ProductDto> productPageDto = (List<ProductDto>) genericResponseDto.getResponse();

        assertTrue(genericResponseDto.getStatus());
        assertEquals(7,productPageDto.size() );
    }

    @Test
    void getProductById() {
        ResponseEntity<?> responseEntity = this.productController.getProductById(1L);
        GenericResponseDto genericResponseDto = (GenericResponseDto) responseEntity.getBody();
        assert genericResponseDto != null;
        ProductDto productPageDto = (ProductDto) genericResponseDto.getResponse();

        assertTrue(genericResponseDto.getStatus());
        assertEquals("OSTER-PLU:3064695",productPageDto.getReference() );
    }
}