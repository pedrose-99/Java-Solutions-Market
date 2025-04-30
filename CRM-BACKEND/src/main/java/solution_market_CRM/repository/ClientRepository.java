package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import solution_market_CRM.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{

}
