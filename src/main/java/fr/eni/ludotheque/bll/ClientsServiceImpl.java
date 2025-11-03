package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.ClientDto;
import fr.eni.ludotheque.exceptions.EmailAlreadyExists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(ClientDto clientDto) {
//        Version classique sans beanutils :
//        Client client = new Client();
//        client.setLastname(clientDto.lastName());
//        client.setFirstname(clientDto.firstName());
//        client.setEmail(clientDto.email());
//        Address address = new Address();
//        address.setStreet(clientDto.street());
//        address.setCity(clientDto.city());
//        address.setPostalCode(clientDto.postalCode());
//        client.setAddress(address);

        //version avec beanutils
        Address address = new Address();
        BeanUtils.copyProperties(clientDto, address);

        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        client.setAddress(address);

        //bien renvoyer un newClient après le save car il va comprendre l'id auto-généré
        Client newClient = null;
        try {
            clientRepository.save(client);
        }
        catch(DataIntegrityViolationException e) {
            throw new EmailAlreadyExists();
        }
        return newClient;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
    @Override
    public List<Client> findClientByLastNameFirstCharacters(String firstCharacters) {
        return clientRepository.findByLastNameIsStartingWith(firstCharacters);
    }

    @Override
    public Client replaceClientById(Integer id, ClientDto clientDto) {
        Address address = new Address();
        BeanUtils.copyProperties(clientDto, address);

        Client client = clientRepository.findById(id).orElse(null);

        if  (client != null) {
            BeanUtils.copyProperties(clientDto, client);
            client.setAddress(address);
            clientRepository.save(client);
            return client;
        }
        else throw new RuntimeException("Client not found, impossible change");
    }

    @Override
    public Client replaceAddressClientById(Integer id, Address address) {

        Client client = clientRepository.findById(id).orElse(null);

        if  (client != null) {
            Address oldAddress = client.getAddress();
            oldAddress.setCity(address.getCity());
            oldAddress.setStreet(address.getStreet());
            oldAddress.setPostalCode(address.getPostalCode());
            clientRepository.save(client);
            return client;
        }
        else throw new RuntimeException("Client not found, impossible change");
    }

    @Override
    public void deleteClientById(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

}
