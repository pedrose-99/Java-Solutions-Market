package solution_market_CRM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import solution_market_CRM.model.Inventory;
import solution_market_CRM.repository.InventoryRepository;
import solution_market_CRM.repository.SalesRepository;

@Service
public class InventoryService
{
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SalesRepository salesRepository;
    
    public List<Inventory> findAll()
    {
        return inventoryRepository.findAll();
    }

    public <S extends Inventory> S save(S entity) 
    {
        return inventoryRepository.save(entity);
    }

    public Inventory getById(int id)
    {
        return inventoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));

    }

    public long count()
    {
        return inventoryRepository.count();
    }

    public void deleteById(int id)
    {
        if (salesRepository.existsByInventoryId(id)) {
            throw new IllegalStateException("No se puede eliminar el empleado porque tiene ventas asociadas.");
        }
        inventoryRepository.deleteById(id);
    }

    public Inventory updateInventory(int id, Inventory newInventory) 
    {
        Inventory inventory  = this.getById(id);
    
        inventory.setName(newInventory.getName());
        inventory.setStock(newInventory.getStock());
        inventory.setPrice(newInventory.getPrice());
        inventory.setDescription(newInventory.getDescription());

        return inventoryRepository.save(inventory);
    }

    
}
