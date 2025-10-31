package fr.eni.ludotheque.restController;

import fr.eni.ludotheque.bll.RentalService;
import fr.eni.ludotheque.bo.Invoice;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceRestController {

    @NonNull
    private RentalService rentalService;

    public InvoiceRestController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return rentalService.getInvoices();
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable Integer id) {
        Invoice invoice = null;
        try {
            invoice = rentalService.getInvoice(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(invoice);
    }

    @PostMapping("/invoices")
    public ResponseEntity<?> createInvoice(@RequestParam List<Integer> rentalsIds) {

        Invoice invoiceRental = null;
        try {
            invoiceRental = rentalService.createInvoice(rentalsIds);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceRental);
    }
}
