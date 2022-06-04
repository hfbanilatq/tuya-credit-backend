package co.edu.eafit.tuya.service.simulation;

import co.edu.eafit.tuya.dto.*;
import co.edu.eafit.tuya.model.CreditCard;
import co.edu.eafit.tuya.model.Product;
import co.edu.eafit.tuya.model.Simulation;
import co.edu.eafit.tuya.repository.CreditCardRepository;
import co.edu.eafit.tuya.repository.SimulationRepository;
import co.edu.eafit.tuya.security.repository.UserRepository;
import co.edu.eafit.tuya.utils.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SimulationServiceImpl implements SimulationService {
    private final DiscountCalculator discountCalculator = new DiscountCalculator();
    private CreditCardRepository creditCardRepository;
    private SimulationRepository simulationRepository;
    private UserRepository userRepository;

    @Autowired
    public void setCreditCardRepository(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Autowired
    public void setSimulationRepository(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GenericResponseDto simulate(SimulationDto simulationDto) {
        BigDecimal realPrice = BigDecimal.ZERO;
        BigDecimal promotionPrice = BigDecimal.ZERO;
        BigDecimal priceWithCreditCard = BigDecimal.ZERO;

        Set<ProductDto> products = simulationDto.getProducts();

        for (ProductDto productDto : products) {
            realPrice = realPrice.add(productDto.getPrice());
            promotionPrice = promotionPrice.add(
                    this.discountCalculator.calculatePriceWithDiscount(productDto.getPrice(), productDto.getDiscount()));
            priceWithCreditCard = priceWithCreditCard.add(
                    this.discountCalculator.calculatePriceWithDiscount(productDto.getPrice(), productDto.getDiscountWithCreditCard()));
        }

        CreditCard creditCard = this.creditCardRepository.findById(simulationDto.getCreditCardId()).get();
        List<MonthlyFeeDto> fees = new ArrayList<>();
        BigDecimal creditCardInterest = BigDecimal.valueOf(creditCard.getMonthlyInterest() / 100);
        int numberOfInstallment = 1;
        BigDecimal balance = priceWithCreditCard;
        BigDecimal feeMonthly = priceWithCreditCard.divide(BigDecimal.valueOf(simulationDto.getNumberOfInstallments()), 6, RoundingMode.HALF_UP);
        BigDecimal pricePaid = BigDecimal.ZERO;
        for (int i = 0; i < simulationDto.getNumberOfInstallments(); i++) {
            BigDecimal feePrice;
            switch (numberOfInstallment) {
                case 1:
                    feePrice = feeMonthly;
                    break;
                case 2:
                    feePrice = feeMonthly.add(priceWithCreditCard.multiply(creditCardInterest)).add(balance.multiply(creditCardInterest));
                    break;
                default:
                    feePrice = feeMonthly.add(balance.multiply(creditCardInterest));
            }

            if (creditCard.getFeeValue().compareTo(BigDecimal.ZERO) > 0) {
                feePrice = feePrice.add(creditCard.getFeeValue());
            }
            pricePaid = pricePaid.add(feePrice);
            balance = balance.subtract(feeMonthly);
            fees.add(MonthlyFeeDto.builder()
                    .balance(balance)
                    .feeNumber(numberOfInstallment)
                    .feeValue(feePrice)
                    .build());
            numberOfInstallment++;
        }

        return new GenericResponseDto(true, "Simulacion completada", SimulationResultDto.builder()
                .promotionPrice(promotionPrice)
                .monthlyFees(fees)
                .pricePaid(pricePaid)
                .priceWithCreditCard(priceWithCreditCard)
                .realPrice(realPrice)
                .build());


    }

    @Override
    public GenericResponseDto save(SimulationDto simulationDto) {
        Simulation simulation = this.simulationRepository.save(this.mapFromDto(simulationDto));
        simulationDto.setId(simulation.getId());
        return new GenericResponseDto(true, "Guardado exitosamente", simulationDto);
    }

    @Override
    public GenericResponseDto getSimulations(int id) {
        List<SimulationDto> simulationDtos = this.simulationRepository.findAllByUser_Id(id).stream().map(this::mapToDto).collect(Collectors.toList());

        return new GenericResponseDto(true, "Obtenidas exitosamente", simulationDtos);
    }

    @Override
    public GenericResponseDto getSimulationsPage(Pageable pageable) {
        return new GenericResponseDto(true, "", this.simulationRepository.findAll(pageable));
    }

    private Simulation mapFromDto(SimulationDto simulationDto) {
        return Simulation.builder()
                .products(simulationDto.getProducts().stream().map(productDto -> Product.builder()
                        .description(productDto.getDescription())
                        .id(productDto.getId())
                        .price(productDto.getPrice())
                        .discount(productDto.getDiscount())
                        .discountWithCreditCard(productDto.getDiscountWithCreditCard())
                        .imageUrl(productDto.getImageUrl())
                        .reference(productDto.getReference())
                        .warehouse(productDto.getWarehouse())
                        .build()).collect(Collectors.toSet()))
                .creditCard(this.creditCardRepository.findById(simulationDto.getCreditCardId()).get())
                .user(userRepository.findById(simulationDto.getUserId()).get())
                .numberOfInstallments(simulationDto.getNumberOfInstallments())
                .build();
    }

    private SimulationDto mapToDto(Simulation simulation) {
        return SimulationDto.builder()
                .id(simulation.getId())
                .products(simulation.getProducts().stream().map(product -> ProductDto.builder()
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
                        .build()).collect(Collectors.toSet()))
                .creditCardId(simulation.getCreditCard().getId())
                .numberOfInstallments(simulation.getNumberOfInstallments())
                .userId(simulation.getUser().getId())
                .createdAt(simulation.getCreatedAt())
                .creditCardName(simulation.getCreditCard().getType())
                .build();
    }


}
