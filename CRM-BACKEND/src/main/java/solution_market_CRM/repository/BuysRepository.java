package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import solution_market_CRM.model.Buys;

public interface BuysRepository extends JpaRepository<Buys, Integer>
{
    @Query ("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Buys b WHERE b.product.product_id = :product_id")
    boolean existsByProductId(@Param("productId") int productId); 
    
    @Query ("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Buys b WHERE b.employee.employee_id = :employee_id")
    boolean existsByEmployeeId(@Param("employeeId") int employeeId); 
}
