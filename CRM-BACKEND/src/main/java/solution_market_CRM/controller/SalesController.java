package solution_market_CRM.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import solution_market_CRM.model.Sales;
import solution_market_CRM.service.SalesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/sales/")
public class SalesController
{
    @Autowired
    public SalesService salesService;

    @GetMapping("sales/")
    public ResponseEntity<List<Sales>> getAllSales()
    {
        return ResponseEntity.ok(salesService.findAll());
    }
    
    @PostMapping("sales/")
    public ResponseEntity<Sales> saveSales(@RequestBody Sales sale) 
    {
        try {
            Sales salesSave = salesService.save(sale);
            return ResponseEntity.created(new URI("/api/sales/" + salesSave.getSales_id())).body(salesSave);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("sales/{sales_id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable int sales_id) 
    {
        Optional<Sales> sales = salesService.getSalesById(sales_id);
        if (sales.isPresent())
        {
            return ResponseEntity.ok(sales.get());
        }
        return (ResponseEntity<Sales>) ResponseEntity.badRequest();
    }

    @DeleteMapping("sales/{sales_id}")
    public void deleteSales(@PathVariable int sales_id)
    {
        salesService.deleteById(sales_id);
    }
}
