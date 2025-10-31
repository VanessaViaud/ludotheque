package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Invoice;
import fr.eni.ludotheque.bo.Rental;
import fr.eni.ludotheque.dto.RentalDto;

import java.util.List;

public interface RentalService {

    Rental addRental(RentalDto rentalDto);

    Rental endRental(Integer RentalId);

    Invoice createInvoice(List<Integer> rentalsIds);

    void payRental(Integer RentalId);

    Invoice getInvoice(Integer id);

    List<Invoice> getInvoices();
}
