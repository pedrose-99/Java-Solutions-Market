package solution_market_CRM.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import solution_market_CRM.model.Employee;
import solution_market_CRM.model.Employee;
import solution_market_CRM.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() 
    {
        return employeeRepository.findAll();
    }

    public long count() 
    {
        return employeeRepository.count();
    }

    public Employee getById(Integer id) 
    {
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));

    }

    public void deleteById(Integer id) 
    {
        employeeRepository.deleteById(id);
    }

    public void delete(Employee entity) 
    {
        employeeRepository.delete(entity);
    }
    
    public <S extends Employee> S save(S entity) 
    {
        return employeeRepository.save(entity);
    }

    public Employee updateEmployee(Integer id, Employee newEmployee) 
    {
        Employee employee  = this.getById(id);
    
        employee.setName(newEmployee.getName());
        employee.setLast_name(newEmployee.getLast_name());
        employee.setAddress(newEmployee.getAddress());
        employee.setEmail(newEmployee.getEmail());
        employee.setPhone_number(newEmployee.getPhone_number());
        employee.setType(newEmployee.getType());

        return employeeRepository.save(employee);
    }

    
}
