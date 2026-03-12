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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import solution_market_CRM.model.Buys;
import solution_market_CRM.service.BuysService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/buys/")
public class BuyController 
{
    @Autowired
    public BuysService buysService;

    @GetMapping("buys/")
    public ResponseEntity<List<Buys>> getAllBuys()
    {
        return ResponseEntity.ok(buysService.findAll());
    }

    @PostMapping("buys/")
    public ResponseEntity<Buys> saveBuys(@RequestBody Buys buy)
    {
        try
        {
            Buys buySave = buysService.save(buy);
            return ResponseEntity.created(new URI("/api/buys/" + buySave.getBuy_id())).body(buySave);
        }
        catch(URISyntaxException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("buys/{buys_id}")
    public ResponseEntity<Buys> getBuyById(@PathVariable int buy_id)
    {
        Optional<Buys> buys = buysService.getBuysById(buy_id);
        if(buys.isPresent())
        {
            return ResponseEntity.ok(buys.get());
        }
        return (ResponseEntity<Buys>) ResponseEntity.badRequest();
    }

    @DeleteMapping("buys/{buys_id}")
    public void deleteSales(@PathVariable int buys_id)
    {
        buysService.deleteById(buys_id);
    }

}
private ResponseEntity<Buys> getByById(int buy_id) {
    return null;
}

private ResponseEntity<Buys> getBuyById(int buy_id) {
    return null;
}