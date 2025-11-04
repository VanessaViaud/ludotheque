package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
