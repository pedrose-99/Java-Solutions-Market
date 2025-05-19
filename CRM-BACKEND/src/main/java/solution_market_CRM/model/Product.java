package solution_market_CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "products")
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

    private String name;
    private int stock;
    private String description;
    private float price;

    @ManyToOne
    @JoinColumn (name = "provider_id")
    private Provider provider;

    public Product() {
    }

    public Product(String name, int stock, String description, float price, Provider provider) {
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
