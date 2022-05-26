package co.edu.eafit.tuya.service.creditcard;

import co.edu.eafit.tuya.dto.CreditCardDto;
import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.dto.ProductDto;
import co.edu.eafit.tuya.model.CreditCard;
import co.edu.eafit.tuya.model.Product;
import co.edu.eafit.tuya.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private CreditCardRepository creditCardRepository;

    @Autowired
    public void setCreditCardRepository(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public GenericResponseDto getCreditCards() {
        return new GenericResponseDto(true, "Consulta exitosa",
                this.creditCardRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList()));
    }

    @Override
    public GenericResponseDto getCreditCardById(Short id) {
        Optional<CreditCard> optionalCreditCard = this.creditCardRepository.findById(id);
        return optionalCreditCard.map(creditCard -> new GenericResponseDto(true, "", this.mapToDto(creditCard)))
                .orElseGet(() -> new GenericResponseDto(false, "La tarjeta no se ha encontrado en la base de datos", null));

    }

    private CreditCardDto mapToDto(CreditCard creditCard) {
        return CreditCardDto.builder()
                .id(creditCard.getId())
                .effectiveAnnualInterest(creditCard.getEffectiveAnnualInterest())
                .feeValue(creditCard.getFeeValue())
                .maxFee(creditCard.getMaxFee())
                .monthlyInterest(creditCard.getMonthlyInterest())
                .type(creditCard.getType())
                .build();
    }
}
