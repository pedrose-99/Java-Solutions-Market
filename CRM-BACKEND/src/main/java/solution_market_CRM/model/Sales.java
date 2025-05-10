package solution_market_CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "sales")
public class Sales 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sales_id;

    private float total_price;
    private int total_sold;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Inventory product;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public Sales() {
    }

    public Sales(float total_price, int total_sold, Client client, Inventory product, Employee employee) {
        this.total_price = total_price;
        this.total_sold = total_sold;
        this.client = client;
        this.product = product;
        this.employee = employee;
    }

    public int getSales_id() {
        return sales_id;
    }

    public void setSales_id(int sales_id) {
        this.sales_id = sales_id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getTotal_sold() {
        return total_sold;
    }

    public void setTotal_sold(int total_sold) {
        this.total_sold = total_sold;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Inventory getProduct() {
        return product;
    }

    public void setProduct(Inventory product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

}
