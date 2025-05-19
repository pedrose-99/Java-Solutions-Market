package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import solution_market_CRM.model.Provider;

//Indico que es el Repository
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>
{
    
}
