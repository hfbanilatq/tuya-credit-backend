package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list/pagination")
    public ResponseEntity<?> getProductsPage(@RequestParam(required = false) String description,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        GenericResponseDto response = null;
        if (description == null) {
            response = this.productService.getProductsPage(paging);

            if (response.getStatus()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProducts() {
        GenericResponseDto response = this.productService.getProducts();
        if (response.getStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        GenericResponseDto response = this.productService.getProductById(id);
        if (response.getStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
