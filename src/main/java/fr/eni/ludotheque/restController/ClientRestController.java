package fr.eni.ludotheque.restController;

import fr.eni.ludotheque.bll.ClientsService;
import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDto;
import fr.eni.ludotheque.exceptions.ClientNotFound;
import fr.eni.ludotheque.exceptions.EmailAlreadyExists;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// un mix de @Controller et de @ResponseBody
public class ClientRestController {

    @NonNull
    private ClientsService clientsService;

    public ClientRestController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = clientsService.findAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    //à modifier pour garder une ResponseEntity<Client> : il faudra créer une APiResponse comme en Kotlin avec un code, un message, et un typer générique qui pourra renvoyer un client ou autre.
    //et là on pourra injecter notre erreur dans le message de l'apiResponse : ResponseEntity<ApiResponse<Client>>
    @PostMapping("/clients")
    public ResponseEntity<?> addClient(@Valid @RequestBody ClientDto clientDto, BindingResult bindingResult) throws ClientNotFound {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Client client = null;
        try {
            client = clientsService.addClient(clientDto);
        } catch (EmailAlreadyExists e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("L'email existe déjà");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @DeleteMapping("/clients/{id}")
    //NB : ici on devrait laisser id en String et convertir après car en http on a que du Strng
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientsService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto) {
        try {
            clientsService.replaceClientById(id, clientDto);
        } catch (ClientNotFound e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<?> updateClientAddress(@PathVariable Integer id, @RequestBody Address address) {
        try {
            clientsService.replaceAddressClientById(id, address);
            ;
        } catch (ClientNotFound e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
