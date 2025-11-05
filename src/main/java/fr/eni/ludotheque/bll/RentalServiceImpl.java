package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Copy;
import fr.eni.ludotheque.bo.Invoice;
import fr.eni.ludotheque.bo.Rental;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dal.CopyRepository;
import fr.eni.ludotheque.dal.InvoiceRepository;
import fr.eni.ludotheque.dal.RentalRepository;
import fr.eni.ludotheque.dto.RentalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class RentalServiceImpl implements RentalService {

    private CopyService copyService;

    @Autowired
    public void setCopyServiceImpl(CopyService copyService) {
    }

    private RentalRepository rentalRepository;

    @Autowired
    public void setRentalRepository(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    private CopyRepository copyRepository;

    @Autowired
    public void setCopyRepository(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    private InvoiceRepository invoiceRepository;

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Rental addRental(RentalDto rentalDto) {

        Rental rental = new Rental();
        rental.setStartDate(LocalDate.now());
        rental.setPricePerDay(rentalDto.pricePerDay());
        Client client = clientRepository.findById(rentalDto.clientId()).orElse(null);
        assert client != null;
        rental.setClient(client);
        Copy copy = copyRepository.findById(rentalDto.copieId()).orElse(null);
        assert copy != null;
        rental.setCopy(copy);
        copy.setAvailable(false);

        copyRepository.save(copy);

        return rentalRepository.save(rental);
    }

    @Override
    public Rental endRental(String RentalId) {
        Rental rental = rentalRepository.findById(RentalId).orElse(null);
        assert rental != null;
        rental.setEndDate(LocalDate.now());
        Copy copy = rental.getCopy();
        copyService.returnCopy(copy.get_id());
        return rental;
    }

    @Override
    public Invoice createInvoice(List<String> rentalsIds) {
        double totalAmount = 0.0;

        for (String rentalId : rentalsIds) {
            Rental rental = rentalRepository.findById(rentalId).orElse(null);
            if (rental != null) {
                long duration = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
                totalAmount += rental.getPricePerDay() * duration;
            }
        }
        Invoice invoice = new Invoice();
        invoice.setAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }

    @Override
    public void payRental(String InvoiceId) {
        Invoice invoice = invoiceRepository.findById(InvoiceId).orElse(null);
        assert invoice != null;
        invoice.setPaymentDate(LocalDate.now());
    }

    @Override
    public Invoice getInvoice(String id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

}
