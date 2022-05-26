package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.service.creditcard.CreditCardService;
import co.edu.eafit.tuya.service.documenttype.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {
    private CreditCardService creditCardService;

    @Autowired
    public void setCreditCardService(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }


    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        return new ResponseEntity<>(this.creditCardService.getCreditCards(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable Short id) {
        return new ResponseEntity<>(this.creditCardService.getCreditCardById(id), HttpStatus.OK);
    }
}
