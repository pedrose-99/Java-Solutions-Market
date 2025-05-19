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
    private String descripcion;
    private int precio_unitario;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name= "employee_id")
    private Employee employee;

    public Buys() {
    }

    public Buys(int stock, String descripcion, int precio_unitario, Product product, Employee employee) {
        this.stock = stock;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
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
