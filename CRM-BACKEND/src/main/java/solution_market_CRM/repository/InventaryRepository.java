package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import solution_market_CRM.model.Inventary;

public interface InventaryRepository extends JpaRepository<Inventary, Long> 
{

}
