package solution_market_CRM.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import solution_market_CRM.model.Inventory;
import solution_market_CRM.service.InventoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/inventory/")
public class InventoryController 
{
    @Autowired
    public InventoryService inventoryService;

    @GetMapping("inventory-products/")
    public ResponseEntity<List<Inventory>> getAllEmployees() 
    {
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @PostMapping("inventory-products/")
    public ResponseEntity<Inventory> saveEmployee(@RequestBody Inventory inventory) {
        try {
            Inventory inventorySave = inventoryService.save(inventory);
            return ResponseEntity.created(new URI("/inventory-product" + inventorySave.getProduct_id())).body(inventorySave);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("inventory-products/{product_id}")
    public ResponseEntity<Inventory> getEmployeeById(@PathVariable int product_id)
    {
        Inventory inventory = inventoryService.getById(product_id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("inventory-products/{product_id}")
    public ResponseEntity<Inventory> updateEmployee(@PathVariable int product_id, @RequestBody Inventory updatedInventory)
    {
        Inventory inventory = inventoryService.updateInventory(product_id, updatedInventory);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("inventory-products/{product_id}")
    public void deleteEmployee(@PathVariable int product_id)
    {
        inventoryService.deleteById(product_id);
    }
}
