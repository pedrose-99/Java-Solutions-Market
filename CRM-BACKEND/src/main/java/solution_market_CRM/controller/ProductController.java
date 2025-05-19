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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import solution_market_CRM.model.Product;
import solution_market_CRM.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products/")
public class ProductController 
{
    @Autowired
    public ProductService productService;

    @GetMapping("products/")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("products/")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        try
        {
            Product productSave = productService.save(product);
            return ResponseEntity.created(new URI("/products" +productSave.getProduct_id())).body(productSave);
        }
        catch(URISyntaxException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("products/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable int product_id)
    {
        Optional<Product> product = productService.getProductById(product_id);
        if(product.isPresent())
        {
            return ResponseEntity.ok(product.get());
        }
        return(ResponseEntity<Product>) ResponseEntity.badRequest();
    }

    @PutMapping("products/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int product_id, @RequestBody Product updateProduct)
    {
        Product product = productService.updateProduct(product_id, updateProduct);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("products/{product_id}")
    public void deleteProduct(@PathVariable int product_id)
    {
        productService.deleteById(product_id);
    }
}
