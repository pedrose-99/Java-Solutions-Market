package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solution_market_CRM.model.Inventory;
import solution_market_CRM.model.Sales;
import solution_market_CRM.repository.InventoryRepository;
import solution_market_CRM.repository.SalesRepository;

@Service
public class SalesService
{
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private InventoryRepository productRepository;

    public List<Sales> findAll()
    {
        return salesRepository.findAll();
    }

    public Optional<Sales> getSalesById(int sales_id) {
        return salesRepository.findById(sales_id);
    }

    @Transactional
    public void deleteById (int id)
    {
        Optional<Sales> optionalSale = this.getSalesById(id);
        int newStock;

        if (optionalSale.isPresent())
        {
            Sales sale = optionalSale.get();

            Inventory product = sale.getProduct();
            if (product != null)
            {
                Inventory realProduct = productRepository.findById(product.getProduct_id()).orElseThrow(() -> new RuntimeException("Product not found"));
                newStock = realProduct.getStock() + sale.getTotal_sold();
                realProduct.setStock(newStock);
                productRepository.save(realProduct);
            }
        }
        salesRepository.deleteById(id);
    }

    public long count()
    {
        return salesRepository.count();
    }

    @Transactional
    public Sales save(Sales sale)
    {
        if (sale.getProduct() != null) 
        {
            int productId = sale.getProduct().getProduct_id();
    
            Inventory product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    
            int newStock = product.getStock() - sale.getTotal_sold();
    
            if (newStock < 0) {
                throw new RuntimeException("Stock insuficiente para realizar la venta.");
            }
    
            product.setStock(newStock);
            productRepository.save(product);
        }
    
        return salesRepository.save(sale);
    }

}
