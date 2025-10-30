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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientsServiceTest {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Test de recherche de client en db")
    @Transactional
    public void searchClients() {

        ClientDto clientDto = new ClientDto(
                "nom1",
                "p1",
                "p1.nom1@eni.fr",
                "010101011",
                "rue1",
                "REZE",
                "44400"
        );

        // Act
        Client newClient = clientsService.addClient(clientDto);
        List<Client> clients = clientsService.findClientByLastNameFirstCharacters("no");
        List<Client> clients2 = clientsService.findClientByLastNameFirstCharacters("dgjfj");

        //vérifications sur la recherche par début de nom
        assertNotNull(clients, "la liste des clients existe");
        assertTrue(clients.contains(newClient), "la liste des clients contient bien le client recherché");
        assertTrue(clients2.isEmpty(), "la liste des clients avec erreur de recherche est bien vide");
    }

    @Test
    @DisplayName("Test d’ajout de client en DB")
    @Transactional
    public void testAddClientAndAddresse() {
        // Arrange
        ClientDto clientDto = new ClientDto(
                "nom1",
                "p1",
                "p1.nom1@eni.fr",
                "010101011",
                "rue1",
                "REZE",
                "44400"
        );

        // Act
        Client newClient = clientsService.addClient(clientDto);
        ClientDto clientDtoForReplacement = new ClientDto("bobo", "bibi", "bub@eni.fr", "2222222", "boubouStreet", "babaCity", "4523");
        clientsService.replaceClientById(1, clientDtoForReplacement);

        // Assert

        //vérifications sur la création de clients
        assertNotNull(newClient, "Le client retourné ne doit pas être nul");
        assertNotNull(newClient.getClientNumber(), "Le numéro client doit être généré");
        assertNotNull(newClient.getAddress(), "L’adresse doit être créée");
        assertNotNull(newClient.getAddress().getId(), "L’adresse doit avoir un id généré");

        // vérification que le client a bien été enregistré en base
        Client clientInDb = clientRepository.findById(newClient.getClientNumber()).orElse(null);
        assertNotNull(clientInDb, "Le client doit être présent en base");
        assertEquals("nom1", clientInDb.getLastName());
        assertEquals("REZE", clientInDb.getAddress().getCity());

        //vérifications sur la modification complète d'un client
        assertNotNull(clientDtoForReplacement);
        assertEquals("bibi", clientDtoForReplacement.firstName());


    //EXEMPLE TEST MOCKE
//
//    @Autowired
//    private ClientsService clientsService;
//
//    @MockitoBean
//    private ClientRepository clientRepository;
//
//    @Test
//    @DisplayName("Test de l'ajoute d'un client cas positif")
//    public void testAjouterClientEtAdresseCasPositif() {
//        //Arrange
//        ClientDto clientDto = new ClientDto("nom1", "p1", "p1.nom1@eni.fr",
//                "010101011",
//                "rue1",
//                "44400",
//                "REZE");
//        Client fauxClient = new Client();
//        BeanUtils.copyProperties(clientDto, fauxClient);
//        fauxClient.setAddress(new Address());
//        BeanUtils.copyProperties(clientDto, fauxClient.getAddress());
//        fauxClient.setClientNumber(123);
//        fauxClient.getAddress().setId(456);
//        when(clientRepository.save(any(Client.class))).thenReturn(fauxClient);
//
//        //Act
//        Client newClient = clientsService.addClient(clientDto);
//
//        //Assert
//        assertNotNull(newClient);
//        assertNotNull(newClient.getClientNumber());
//        assertNotNull(newClient.getAddress().getId());
//        assertEquals(456, newClient.getAddress().getId());
    }

}
