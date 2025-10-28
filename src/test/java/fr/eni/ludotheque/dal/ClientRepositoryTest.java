package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Test d'ajout d'un client en DB - cas droit")
    void addClient() {
        //AAA
        // Arrange = préparation du test
        Client client = new Client("Vanessa", "Viaud", "vv@eni.fr");
        client.setPhoneNumber("1234567890");

        //Act = appel de la métthode à tester
        Client newClient = clientRepository.save(client);

        //Assert = vérifier le résultat fourni
        assertNotNull(newClient);
        assertNotNull(newClient.getClientNumber());
        assertEquals(client.getLastname(), newClient.getLastname());

        clientRepository.flush();
        Optional<Client> searchClient = clientRepository.findById(newClient.getClientNumber());
        assertTrue(searchClient.isPresent());
        assertEquals(client,  searchClient.get());

    }
}
