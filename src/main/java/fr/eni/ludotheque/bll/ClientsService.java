package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDto;

import java.util.List;

public interface ClientsService {

    Client addClient(ClientDto clientDto);

    List<Client> findAllClients();

    List<Client> findClientByLastNameFirstCharacters(String firstCharacters);
    Client replaceClientById(String id, ClientDto clientDto);

    Client replaceAddressClientById(String id, Address address);

    void deleteClientById(String id);

    Client findClientById(String id);
}
