package solution_market_CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "provider")
public class Provider 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int provider_id;
    
    private String name;
    private String last_name;
    private String address;
    private String phone_number;
    private String email;

    public Provider() {}

    public Provider(String name, String last_name, String address, String phone_number, String email) {
        this.name = name;
        this.last_name = last_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
