package fr.eni.ludotheque.restController;

import fr.eni.ludotheque.bll.RentalService;
import fr.eni.ludotheque.bo.Rental;
import fr.eni.ludotheque.dto.RentalDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalRestController {

    @NonNull
    private RentalService rentalService;

    public RentalRestController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @PostMapping("/rentals")
    public ResponseEntity<?> rental(@Valid @RequestBody RentalDto rentalDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Rental rental = null;
        try {
            rental = rentalService.addRental(rentalDto);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

}
