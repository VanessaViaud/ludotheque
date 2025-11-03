package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDto;

import java.util.List;

public interface ClientsService {

    Client addClient(ClientDto clientDto);

    List<Client> findAllClients();

    List<Client> findClientByLastNameFirstCharacters(String firstCharacters);
    void replaceClientById(Integer id, ClientDto clientDto);

    void replaceAddressClientById(Integer id, Address address);

    void deleteClientById(Integer id);

}
