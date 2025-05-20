package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solution_market_CRM.bills.BillBuy;
import solution_market_CRM.model.Buys;
import solution_market_CRM.model.Inventory;


import solution_market_CRM.model.Product;
import solution_market_CRM.repository.BuysRepository;
import solution_market_CRM.repository.EmployeeRepository;
import solution_market_CRM.repository.InventoryRepository;
import solution_market_CRM.repository.ProductRepository;

@Service
public class BuysService 
{
    @Autowired
    private BuysRepository buysRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InventoryService inventoryService;

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
        List<Inventory> inventoryList = inventoryService.findAll();
        Boolean newInventory = true;
        BillBuy buyBill = new BillBuy();

        if(buy.getProduct() != null)
        {
            int productId = buy.getProduct().getProduct_id();

            product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            int newStock = product.getStock() - buy.getStock();

            for (Inventory inventory : inventoryList)
            {
                if (inventory.getName().equals(product.getName()))
                {
                    inventory.setStock(buy.getStock()+ inventory.getStock());
                    inventoryService.save(inventory);
                    newInventory = false;
                }
            }
            if(newStock < 0 )
            {
                throw new RuntimeException("Stock insuficiente para realizar la compra.");
            }
            if (newInventory)
            {
                float price = (buy.getTotal_price()/buy.getStock()) * 1.20f;
                Inventory inventory = new Inventory(product.getName(), buy.getStock(), product.getDescription(), price);
                inventoryService.save(inventory);
            }
            product.setStock(newStock);
            productRepository.save(product);
        }

        Buys buyReturn = buysRepository.save(buy);
        buyBill.generateBill(buy, product);
        return buyReturn;
    }

}