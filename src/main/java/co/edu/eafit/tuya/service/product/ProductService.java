package co.edu.eafit.tuya.service.product;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public GenericResponseDto getProducts();
    public GenericResponseDto getProductById(Long id);
    public GenericResponseDto getProductsPage(Pageable pageable);
}
