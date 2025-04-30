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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping ("/api/client/")
public class ClientController
{
    @Autowired
    private ClientService clientService;

    @GetMapping("clients/")
    private ResponseEntity<List<Client>> getAllClients()
    {
        return ResponseEntity.ok(clientService.findAll());
    }

    @PostMapping
    private ResponseEntity<Client> saveClient (@RequestBody Client client)
    {
        try {
            Client clientSave = clientService.save(client);
            return ResponseEntity.created(new URI("/client" + clientSave.getClient_id())).body(clientSave);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
