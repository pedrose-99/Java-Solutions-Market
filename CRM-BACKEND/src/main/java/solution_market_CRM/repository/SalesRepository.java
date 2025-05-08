package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import solution_market_CRM.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>
{   
}
