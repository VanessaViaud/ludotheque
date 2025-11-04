package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Invoice;
import fr.eni.ludotheque.bo.Rental;
import fr.eni.ludotheque.dto.RentalDto;

import java.util.List;

public interface RentalService {

    Rental addRental(RentalDto rentalDto);

    Rental endRental(String RentalId);

    Invoice createInvoice(List<String> rentalsIds);

    void payRental(String RentalId);

    Invoice getInvoice(String id);

    List<Invoice> getInvoices();
}
