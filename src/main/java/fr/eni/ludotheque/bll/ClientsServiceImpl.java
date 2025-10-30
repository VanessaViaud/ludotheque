package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.ClientDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findClientByLastNameFirstCharacters(String firstCharacters) {
        return clientRepository.findByLastNameIsStartingWith(firstCharacters);
    }

    @Override
    public void replaceClientById(Integer id, ClientDto clientDto) {
        Address address = new Address();
        BeanUtils.copyProperties(clientDto, address);

        Client client = clientRepository.findById(id).orElse(null);

        if  (client != null) {
            BeanUtils.copyProperties(clientDto, client);
            client.setAddress(address);
            clientRepository.save(client);
        }
        else throw new RuntimeException("Client not found, impossible change");

    }
}
