package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
