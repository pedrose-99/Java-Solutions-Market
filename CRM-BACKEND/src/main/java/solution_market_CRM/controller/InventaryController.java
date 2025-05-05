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

import solution_market_CRM.model.Inventary;
import solution_market_CRM.service.InventaryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/inventary/")
public class InventaryController 
{
    @Autowired
    private InventaryService inventaryService;

    @GetMapping("inventary-products/")
    private ResponseEntity<List<Inventary>> getAllEmployees() 
    {
        return ResponseEntity.ok(inventaryService.findAll());
    }

    @PostMapping("inventary-products/")
    private ResponseEntity<Inventary> saveEmployee(@RequestBody Inventary inventary) {
        try {
            Inventary inventarySave = inventaryService.save(inventary);
            return ResponseEntity.created(new URI("/inventary-product" + inventarySave.getProduct_id())).body(inventarySave);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("inventary-products/{product_id}")
    private ResponseEntity<Inventary> getEmployeeById(@PathVariable Long product_id)
    {
        Inventary inventary = inventaryService.getById(product_id);
        return ResponseEntity.ok(inventary);
    }

    @PutMapping("inventary-products/{product_id}")
    private ResponseEntity<Inventary> updateEmployee(@PathVariable Long product_id, @RequestBody Inventary updatedInventary)
    {
        Inventary inventary = inventaryService.updateInventary(product_id, updatedInventary);
        return ResponseEntity.ok(inventary);
    }

    @DeleteMapping("inventary-products/{product_id}")
    private void deleteEmployee(@PathVariable Long product_id)
    {
        inventaryService.deleteById(product_id);
    }
}
