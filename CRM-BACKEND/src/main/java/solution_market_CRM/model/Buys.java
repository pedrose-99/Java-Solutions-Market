package solution_market_CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "buys")
public class Buys 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int buy_id;

    private int stock;
    private int total_price;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name= "employee_id")
    private Employee employee;

    public Buys() {
    }

    public Buys(int stock, int total_price, Product product, Employee employee) {
        this.stock = stock;
        this.total_price = total_price;
        this.product = product;
        this.employee = employee;
    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
