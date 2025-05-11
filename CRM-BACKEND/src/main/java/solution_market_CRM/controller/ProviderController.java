package solution_market_CRM.controller;

import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.classfile.Module.Provide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import solution_market_CRM.model.Provider;
import solution_market_CRM.service.ProviderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/provider/")
public class ProviderController 
{
    @Autowired
    public ProviderService providerService;

    @GetMapping("providers/")
    public ResponseEntity<List<Provider>> getAllProviders()
    {
        return ResponseEntity.ok(providerService.findAll());
    }

    @PostMapping("providers/{provider_id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable int provider_id)
    {
        Optional<Provider> provider = providerService.getById(provider_id);
        if(provider.isPresent())
        {
            return ResponseEntity.ok(provider.get());
        }
        return(ResponseEntity<Provider>) ResponseEntity.badRequest();
    }

    @PutMapping("providers/{provider_id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable int provider_id, @RequestBody Provider updatedProvider)
    {
        Provider provider = providerService.updateProvider(provider_id, updatedProvider);
        return ResponseEntity.ok(provider);
    }

    @DeleteMapping("providers/{provider_id}")
    public void deleteProvider(@PathVariable int provider_id)
    {
        providerService.deleteById(provider_id);
    }
    
}
