package co.edu.eafit.tuya.service.simulation;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.dto.ProductDto;
import co.edu.eafit.tuya.dto.SimulationDto;
import co.edu.eafit.tuya.dto.SimulationResultDto;
import co.edu.eafit.tuya.repository.CreditCardRepository;
import co.edu.eafit.tuya.repository.SimulationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.when;
@SpringBootTest
class SimulationServiceImplTest {
    @Mock
    private SimulationRepository simulationRepository;
    @Mock
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SimulationServiceImpl simulationService;

    @Test
    void setSimulationRepository() {
    }

    @Test
    void simulate() {
        Set<ProductDto> products = new HashSet<>();
        products.add(ProductDto.builder()
                .price(BigDecimal.valueOf(500000L))
                .discountWithCreditCard(30.0)
                .discount(22.0)
                .build());
        SimulationDto simulationDto = SimulationDto.builder()
                .creditCardId((short) 1)
                .numberOfInstallments(12)
                .products(products)
                .build();

        SimulationResultDto simulationResultDto = SimulationResultDto.builder()
                .realPrice(BigDecimal.valueOf(500000))
                .promotionPrice(BigDecimal.valueOf(390000.00))
                .pricePaid(BigDecimal.valueOf(708165.8333372534))
                .build();
        GenericResponseDto genericResponseDto = this.simulationService.simulate(simulationDto);

        SimulationResultDto response = (SimulationResultDto) genericResponseDto.getResponse();

        assertNotNull(genericResponseDto);
        assertEquals(simulationResultDto.getPricePaid(), response.getPricePaid());
        assertEquals(simulationResultDto.getRealPrice(), response.getRealPrice());

    }

    @Test
    void save() {
    }

    @Test
    void getSimulations() {
    }

    @Test
    void getSimulationsPage() {
    }
}