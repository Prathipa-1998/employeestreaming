package in.co.employee.employeestreaming.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.employee.employeestreaming.Model.Employee;
import in.co.employee.employeestreaming.repository.EmployeeRepository;

@RestController
@RequestMapping("/optionatest")
public class OptionalExample {
	@Autowired
	private EmployeeRepository emprepository;
	
	

	@GetMapping("/getEmployeeById")
	public ResponseEntity<?> getEmployeeById(@PathVariable String id)
	{
		Optional<Employee> employeelist=emprepository.findById(id);
		if(!employeelist.isEmpty())
		{
			//String name=Optional.ofNullable(employeelist.get().getFname()).orElse("null");
			//here we use orElseGet method alternate of orElse method both are same but orElseGet 
			//method takes suppier function as a argument
			String name=Optional.ofNullable(employeelist.get().getFname()).orElseGet(()->"null");
			return new ResponseEntity<>(name,HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("given value it not present in the db",HttpStatus.NOT_FOUND);
	}
	

}
}