package co.edu.eafit.tuya.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ProductPageDto {
    private int currentPage;
    private long totalItems;
    private int totalPages;
    @Singular
    private List<ProductDto> products;
}
