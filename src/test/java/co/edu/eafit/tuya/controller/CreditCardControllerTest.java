package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.dto.CreditCardDto;
import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.service.creditcard.CreditCardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreditCardControllerTest {
    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private CreditCardController creditCardController;
    @DisplayName("Test Spring @Autowired Integration")
    @Test
    public void contextLoads() throws Exception {
        assertNotNull(creditCardController);
    }

    @Test
    void whenGetListCorrectly_thenShouldReturnStatusOk() {
        BigDecimal feeValue = BigDecimal.valueOf(20300L);

        List<CreditCardDto> creditCards = new ArrayList<>();
        creditCards.add(CreditCardDto.builder()
                .id((short) 1)
                .effectiveAnnualInterest(20.9)
                .feeValue(feeValue)
                .maxFee(48)
                .monthlyInterest(2.18)
                .type("Tarjeta Exito")
                .build());
        GenericResponseDto genericResponseDto = new GenericResponseDto(true, "Consulta Exitosa", creditCards);
        when(creditCardService.getCreditCards()).thenReturn(genericResponseDto);

        ResponseEntity<?> response = this.creditCardController.getList();
        GenericResponseDto genericResponseDto1 = (GenericResponseDto) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void whenGetById_thenShouldReturnStatusOk() {
        BigDecimal feeValue = BigDecimal.valueOf(20300L);
        CreditCardDto creditCardDto =CreditCardDto.builder()
                .id((short) 1)
                .effectiveAnnualInterest(20.9)
                .feeValue(feeValue)
                .maxFee(48)
                .monthlyInterest(2.18)
                .type("Tarjeta Exito")
                .build();
        GenericResponseDto genericResponseDto = new GenericResponseDto(true, "Consulta Exitosa", creditCardDto);
        when(creditCardService.getCreditCardById(anyShort())).thenReturn(genericResponseDto);

        ResponseEntity<?> response = this.creditCardController.getById((short) 1);
        GenericResponseDto genericResponseDto1 = (GenericResponseDto) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assert genericResponseDto1 != null;
        assertEquals(genericResponseDto.getResponse(), genericResponseDto1.getResponse());
    }
}