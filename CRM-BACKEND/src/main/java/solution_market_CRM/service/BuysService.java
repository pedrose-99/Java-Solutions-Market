package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;
import solution_market_CRM.model.Buys;

import solution_market_CRM.model.Product;
import solution_market_CRM.repository.BuysRepository;
import solution_market_CRM.repository.EmployeeRepository;
import solution_market_CRM.repository.ProductRepository;

public class BuysService 
{
    @Autowired
    private BuysRepository buysRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Buys> findAll()
    {
        return buysRepository.findAll();
    }

    public Optional<Buys> getBuysById(int buy_id)
    {
        return buysRepository.findById(buy_id);
    }

    @Transactional
    public void deleteById(int id)
    {
        Optional<Buys> optionalBuy = this.getBuysById(id);

        if(optionalBuy.isPresent())
        {
            Buys buy = optionalBuy.get();

            Product product = buy.getProduct();
            if(product != null)
            {
                Product realProduct = productRepository.findById(product.getProduct_id()).orElseThrow(() -> new RuntimeException("Product not found"));
                int newStock = realProduct.getStock() + buy.getStock();
                realProduct.setStock(newStock);
                productRepository.save(realProduct);

            buysRepository.deleteById(id);
            }
        }
    }

    public long count()
    {
        return buysRepository.count();
    }

    @Transactional
    public Buys save(Buys buy)
    {
        Product product = null;

        if(buy.getProduct() != null)
        {
            int productId = buy.getProduct().getProduct_id();

            product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            int newStock = product.getStock() - buy.getStock();

            if(newStock < 0 )
            {
                throw new RuntimeException("Stock insuficiente para realizar la compra.");
            }

            product.setStock(newStock);
            productRepository.save(product);
        }

        Buys buyReturn = buysRepository.save(buy);
        return buyReturn;
    }

}