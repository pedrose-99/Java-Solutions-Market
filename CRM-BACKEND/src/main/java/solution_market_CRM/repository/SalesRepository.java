package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import solution_market_CRM.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>
{
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Sales s WHERE s.client.client_id = :clientId")
    boolean existsByClientId(@Param("clientId") int clientId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Sales s WHERE s.product.product_id = :inventoryId")
    boolean existsByInventoryId(@Param("inventoryId") int inventoryId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Sales s WHERE s.employee.employee_id = :employeeId")
    boolean existsByEmployeeId(@Param("employeeId") int employeeId);
}
