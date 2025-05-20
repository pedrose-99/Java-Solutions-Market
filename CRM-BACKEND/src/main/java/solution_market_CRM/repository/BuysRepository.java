package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import solution_market_CRM.model.Buys;

public interface BuysRepository extends JpaRepository<Buys, Integer>
{

}
