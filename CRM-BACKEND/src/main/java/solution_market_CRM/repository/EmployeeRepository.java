package solution_market_CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import solution_market_CRM.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
    
}
