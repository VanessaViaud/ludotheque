package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Rental;
import fr.eni.ludotheque.dto.RentalDto;

public interface RentalService {

    Rental addRental(RentalDto rentalDto);
}
