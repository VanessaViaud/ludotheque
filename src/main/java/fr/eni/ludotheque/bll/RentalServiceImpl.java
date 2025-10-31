package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Copy;
import fr.eni.ludotheque.bo.Rental;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dal.CopyRepository;
import fr.eni.ludotheque.dal.RentalRepository;
import fr.eni.ludotheque.dto.RentalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RentalServiceImpl implements RentalService {

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

    private ClientRepository clientRepository;
    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Rental addRental(RentalDto rentalDto) {

        Rental rental = new Rental();
        rental.setStartDate(LocalDateTime.now());
        rental.setPricePerDay(rentalDto.pricePerDay());
        Client client = clientRepository.findById(rentalDto.clientId()).orElse(null);
        assert client != null;
        rental.setClient(client);
        Copy copy = copyRepository.findById(rentalDto.copieId()).orElse(null);
        assert copy != null;
        rental.setCopy(copy);
        copy.setAvailable(rentalDto.available());

        copyRepository.save(copy);

        return rentalRepository.save(rental);
    }


}
