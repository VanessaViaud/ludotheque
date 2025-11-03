package fr.eni.ludotheque.restController;

import fr.eni.ludotheque.bll.ClientsService;
import fr.eni.ludotheque.bo.Address;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDto;
import fr.eni.ludotheque.exceptions.ClientNotFound;
import fr.eni.ludotheque.exceptions.EmailAlreadyExists;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
// un mix de @Controller et de @ResponseBody
public class ClientRestController {

    private final ClientsService clientsService;

    public ClientRestController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/clients")
    public ResponseEntity<ApiResponse<List<Client>>> findAll() {
        List<Client> clients = clientsService.findAllClients();
        return ResponseEntity.ok(new ApiResponse<>(true, "ok", clients));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ApiResponse<Client>> findById(@PathVariable Integer id) {
        Client client = clientsService.findClientById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "ok", client));
    }

    //à modifier pour garder une ResponseEntity<Client> : il faudra créer une APiResponse comme en Kotlin avec un code, un message, et un typer générique qui pourra renvoyer un client ou autre.
    //et là on pourra injecter notre erreur dans le message de l'apiResponse : ResponseEntity<ApiResponse<Client>>
    @PostMapping("/clients")
    public ResponseEntity<ApiResponse<Client>> addClient(@Valid @RequestBody ClientDto clientDto, BindingResult bindingResult) throws ClientNotFound {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(f -> f.getField() + " : " +
                            f.getDefaultMessage()).collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, errors, null));
        }

        Client client = null;
        try {
            client = clientsService.addClient(clientDto);
        } catch (EmailAlreadyExists e) {
            ApiResponse<Client> apiResponseBad = new ApiResponse<>(false, "Email already exists", client);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(apiResponseBad);
        }
        ApiResponse<Client> apiResponse = new ApiResponse<>(true, "ok", client);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/clients/{id}")
    //NB : ici on devrait laisser id en String et convertir après car en http on a que du Strng
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientsService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<ApiResponse<Client>> updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto) {
        Client client = null;
        try {
            client = clientsService.replaceClientById(id, clientDto);
        } catch (ClientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Client : " + id + " not found", null));
        }

        ApiResponse<Client> apiResponse = new ApiResponse<>(true, "ok", client);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<ApiResponse<Client>> updateClientAddress(@PathVariable Integer id, @RequestBody Address address) {
        Client client = null;
        try {
           client = clientsService.replaceAddressClientById(id, address);
            ;
        } catch (ClientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Client : " + id + " not found", null));
        }
        ApiResponse<Client> apiResponse = new ApiResponse<>(true, "ok", client);
        return ResponseEntity.ok(apiResponse);
    }

}
