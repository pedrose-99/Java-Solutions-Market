package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import solution_market_CRM.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> 
{

}
