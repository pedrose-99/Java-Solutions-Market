package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import solution_market_CRM.exception.ResourceNotFound;
import solution_market_CRM.model.Product;
import solution_market_CRM.repository.ProductRepository;

@Service
public class ProductService 
{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int product_id)
    {
        return productRepository.findById(product_id);
    }

    public void deleteById(int id)
    {
        productRepository.deleteById(id);
    }

    public long count()
    {
        return productRepository.count();
    }

    public <S extends Product> S save(S entity)
    {
        return productRepository.save(entity);
    }

    public Product updateProduct(int id, Product newProduct)    
    {
        Optional<Product> optionalProduct = this.getProductById(id);
        
        if(!optionalProduct.isPresent())
        {
            throw new ResourceNotFound("Producto no encontrado con id: " +id);
        }

        Product product = optionalProduct.get();

        product.setName(newProduct.getName());
        product.setStock(newProduct.getStock());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setProvider(newProduct.getProvider());

        return productRepository.save(product);
    }
}
