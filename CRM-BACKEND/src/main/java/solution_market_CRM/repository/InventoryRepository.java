package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import solution_market_CRM.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> 
{

}
