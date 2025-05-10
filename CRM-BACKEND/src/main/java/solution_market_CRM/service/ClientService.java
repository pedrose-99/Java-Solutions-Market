package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import solution_market_CRM.exception.ResourceNotFound;
import solution_market_CRM.model.Client;
import solution_market_CRM.repository.ClientRepository;
import solution_market_CRM.repository.SalesRepository;

@Service
public class ClientService 
{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SalesRepository salesRepository;

    public List<Client> findAll() 
    {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(int id) 
    {
        return clientRepository.findById(id);
    }

    public void deleteById(int id) 
    {
        if (salesRepository.existsByClientId(id)) 
        {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene ventas asociadas.");
        }
        clientRepository.deleteById(id);

    }

    public long count() 
    {
        return clientRepository.count();
    }

    public <S extends Client> S save(S entity) 
    {
        return clientRepository.save(entity);
    }

    public Client updateClient(int id, Client newClient) 
    {
        Optional<Client> optionalClient  = this.getById(id);
    
        if (!optionalClient.isPresent())
        {
            throw new ResourceNotFound("Cliente no encontrado con id: " + id);
        }

        Client client = optionalClient.get();

        client.setName(newClient.getName());
        client.setLast_name(newClient.getLast_name());
        client.setAddress(newClient.getAddress());
        client.setEmail(newClient.getEmail());
        client.setPhone_number(newClient.getPhone_number());
    
        return clientRepository.save(client);
    }

}
