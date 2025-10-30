package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Test d'ajout d'un client en DB - cas droit")
    //@Transactional annotation qui permet de systématiquement faire un rollback à la fin du test.
    //PB : à la fin du test, vu qu'il y a un rollback, on ne peut pas aller en DB vérifier l'insertion du client
    //autre solution : faire un delete du client avant de lancer le nouveau test
    void addClient() {
        //AAA
        // Arrange = préparation du test
        Client client = new Client("Vanessa", "Viaud", "vv@eni.fr", new Address("Saint-Herblain", "2 rue Franklin", "44800"));
        client.setPhoneNumber("1234567890");

        //Act = appel de la métthode à tester
        Client newClient = clientRepository.save(client);
        List<Client> testResearch = clientRepository.findClientsByLastName("vi");
        List<Client> testResearch2 = clientRepository.findClientsByLastName("kjj");

        List<Client> testResearch3 = clientRepository.findByLastNameIsStartingWith("vi");
        List<Client> testResearch4 = clientRepository.findByLastNameIsStartingWith("kjj");

        //Assert = vérifier le résultat fourni
        assertNotNull(newClient);
        assertNotNull(testResearch);
        assertTrue(testResearch2.isEmpty());
        assertNotNull(testResearch3);
        assertTrue(testResearch4.isEmpty());
        assertNotNull(newClient.getClientNumber());
        assertEquals(client.getLastName(), newClient.getLastName());

        clientRepository.flush();
        Optional<Client> searchClient = clientRepository.findById(newClient.getClientNumber());
        assertTrue(searchClient.isPresent());
        assertEquals(client, searchClient.get());

    }
}
