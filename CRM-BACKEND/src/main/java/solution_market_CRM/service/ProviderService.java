package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import solution_market_CRM.exception.ResourceNotFound;
import solution_market_CRM.repository.ProviderRepository;

import solution_market_CRM.model.Provider;

@Service
public class ProviderService 
{
    //Creo un objeto del tipo ProviderRepository que va a implementar la interfaz que ya ec¡xiste de repository pero va a ser con Provider e Integer
    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> findAll()
    {
        return providerRepository.findAll();
    }

    //por si acaso no hay proveedor
    public Optional<Provider> getById(int id)
    {
        return providerRepository.findById(id);
    }

    public void deleteById(int id)
    {
        providerRepository.deleteById(id);
    }

    //devuelve el numero de providers
    public long count()
    {
        return providerRepository.count();
    }

    //S puede ser Provider pero que le falte cualquier cosa menos el id.
    public <S extends Provider> S save(S entity)
    {
        return providerRepository.save(entity);
    }

    public Provider updateProvider(int id, Provider newProvider)
    {
        Optional<Provider> optionalProvider = this.getById(id);

        if(!optionalProvider.isPresent())
        {
            throw new ResourceNotFound("Proveedor no encontrado con id: " +id);
        }

        Provider provider = optionalProvider.get();

        provider.setName(newProvider.getName());
        provider.setLast_name(newProvider.getLast_name());
        provider.setAddress(newProvider.getAddress());
        provider.setEmail(newProvider.getEmail());
        provider.setPhone_number(newProvider.getPhone_number());

        return providerRepository.save(provider);

    }
}
