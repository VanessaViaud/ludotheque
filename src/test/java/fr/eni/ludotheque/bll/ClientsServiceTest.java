package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.ClientDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientsServiceTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientsService clientsService;

    @Test
    @DisplayName("test de l'ajout d'un client - cas positif")
    public void addClientAndAddress() {

        ClientDto clientDto = new ClientDto("name1", "firstname1", "1@eni.fr", "1234567891", "street1", "cp1", "city1");

        Client fauxClient = new Client();
        BeanUtils.copyProperties(clientDto, fauxClient);
        fauxClient.setAddress(new Address());
        BeanUtils.copyProperties(clientDto, fauxClient.getAddress());

        fauxClient.setClientNumber(123);
        fauxClient.getAddress().setId(456);
        when(clientRepository.save(any(Client.class))).thenReturn(fauxClient);

        Client newClient = clientsService.addClient(clientDto);

        assertNotNull(newClient);
        assertNotNull(newClient.getClientNumber());
        assertNotNull(newClient.getAddress().getId());
    }

}
