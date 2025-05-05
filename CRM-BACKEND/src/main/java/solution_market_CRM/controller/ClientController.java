package solution_market_CRM.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import solution_market_CRM.model.Client;
import solution_market_CRM.service.ClientService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/client/")
public class ClientController 
{
    @Autowired
    private ClientService clientService;

    @GetMapping("clients/")
    private ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @PostMapping("clients/")
    private ResponseEntity<Client> saveClient(@RequestBody Client client) {
        try {
            Client clientSave = clientService.save(client);
            return ResponseEntity.created(new URI("/client" + clientSave.getClient_id())).body(clientSave);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("clients/{client_id}")
    private ResponseEntity<Client> getClientById(@PathVariable Long client_id) {
        Client client = clientService.getById(client_id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("clients/{client_id}")
    private ResponseEntity<Client> updateClient(@PathVariable Long client_id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(client_id, updatedClient);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("clients/{client_id}")
    private void deleteClient(@PathVariable Long client_id) 
    {
        clientService.deleteById(client_id);
    }

}
