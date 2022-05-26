package co.edu.eafit.tuya.service.product;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.dto.ProductDto;
import co.edu.eafit.tuya.dto.ProductPageDto;
import co.edu.eafit.tuya.model.Product;
import co.edu.eafit.tuya.repository.ProductRepository;
import co.edu.eafit.tuya.utils.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericResponseDto getProducts() {
        return new GenericResponseDto(true, "",
                this.productRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList()));
    }

    @Override
    public GenericResponseDto getProductById(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        return optionalProduct.map(product -> new GenericResponseDto(true, "", this.mapToDto(product)))
                .orElseGet(() -> new GenericResponseDto(false, "El producto no se ha encontrado en la base de datos", null));
    }

    @Override
    public GenericResponseDto getProductsPage(Pageable pageable) {
        Page<Product> productPage = this.productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        ProductPageDto productPageDto = ProductPageDto.builder()
                .currentPage(productPage.getNumber())
                .totalPages(productPage.getTotalPages())
                .totalItems(productPage.getTotalElements())
                .products(products.stream().map(this::mapToDto).collect(Collectors.toSet()))
                .build();
        return new GenericResponseDto(true, "", productPageDto);
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .id(product.getId())
                .imageUrl(product.getImageUrl())
                .warehouse(product.getWarehouse())
                .discount(product.getDiscount())
                .price(product.getPrice())
                .reference(product.getReference())
                .discountWithCreditCard(product.getDiscountWithCreditCard())
                .priceWithAllPayments(this.discountCalculator.calculatePriceWithDiscount(product.getPrice(), product.getDiscount()))
                .priceWithCreditCard(this.discountCalculator.calculatePriceWithDiscount(product.getPrice(), product.getDiscountWithCreditCard()))
                .build();
    }


}
