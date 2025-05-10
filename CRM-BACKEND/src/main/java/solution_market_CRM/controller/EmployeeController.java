package solution_market_CRM.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import solution_market_CRM.model.Client;
import solution_market_CRM.model.Employee;
import solution_market_CRM.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {
    
    @Autowired
    public EmployeeService employeeService;

    @GetMapping("employees/")
    public ResponseEntity<List<Employee>> getAllEmployees() 
    {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping("employees/")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        try {
            Employee employeeSave = employeeService.save(employee);
            return ResponseEntity.created(new URI("/employee" + employeeSave.getEmployee_id())).body(employeeSave);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("employees/{employee_id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employee_id)
    {
        Employee employee = employeeService.getById(employee_id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("employees/{employee_id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employee_id, @RequestBody Employee updatedEmployee)
    {
        Employee employee = employeeService.updateEmployee(employee_id, updatedEmployee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("employees/{employee_id}")
    public void deleteEmployee(@PathVariable int employee_id)
    {
        employeeService.deleteById(employee_id);
    }
}
