package co.edu.eafit.tuya.service.creditcard;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import org.springframework.data.domain.Pageable;

public interface CreditCardService {
    public GenericResponseDto getCreditCards();
    public GenericResponseDto getCreditCardById(Short id);
}
