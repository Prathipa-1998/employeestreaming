package in.co.employee.employeestreaming.controller;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.employee.employeestreaming.Model.Employee;
import in.co.employee.employeestreaming.repository.EmployeeRepository;

@RestController
@RequestMapping("/test")
public class TestConroller {
	//secregate each employee based on their age
	@Autowired
	private EmployeeRepository employeerepo;
	@GetMapping("/getEmplolistByAge")
	public ResponseEntity<?> getEmplolistByAge()
	{
		List<Employee> emplist=employeerepo.findAll();
		Map<Integer,List<Employee>> secregateByAge=emplist.stream().collect(Collectors.groupingBy(Employee::getAge,Collectors.toList()));
		return new ResponseEntity<>(secregateByAge,HttpStatus.OK);
	}
	//get minimum /maximum/average/count age of employees
	@GetMapping("/fetchMaxMinAvg")
	public ResponseEntity<?> fetchMaxMinAvg()
	{
		List<Employee> emplist=employeerepo.findAll();
		List<Integer> agelist=emplist.stream().map(e->e.getAge()).collect(Collectors.toList());
					return new ResponseEntity<>(agelist.stream().mapToInt(e->e).summaryStatistics().getMax(),HttpStatus.OK);
	}
	//return 2nd and 3rd youngest employee
	@GetMapping("/getYoungestEmployee")
	public ResponseEntity<?> getYoungestEmployee()
	{
		List<Employee> emplist=employeerepo.findAll();
		List<String> youngestEmployee=emplist.stream().sorted(Comparator.comparingInt(Employee::getAge)).skip(1).limit(2).map(e->e.getFname()).collect(Collectors.toList());
		return new ResponseEntity<>(youngestEmployee,HttpStatus.OK);
	}
	
	//fetch all the employee name in a string format and seremate them with _
	@GetMapping("/getEmployeenames")
	public ResponseEntity<?> getEmployeenames()
	{
		List<Employee> emplist=employeerepo.findAll();
		String empname=emplist.stream().map(emp->emp.getFname()).collect(Collectors.joining(","));
		return new ResponseEntity<>(empname,HttpStatus.OK);
	}
	
	//find the duplicate elements
	@GetMapping("/getDups")
	public ResponseEntity<?> getDups()
	{
		List<Employee> emplist=employeerepo.findAll();
		Set<Employee> empset=new HashSet<>();//uniqelements
		List<Employee> duplist=emplist.stream().filter(e->!empset.add(e)).collect(Collectors.toList());//dupelements
		return new ResponseEntity<>(duplist,HttpStatus.OK);
	}
	//sort the employess based on their salaries
	@GetMapping("/getSortedemployees")
	public ResponseEntity<?> getSortedemployees()
	{
		List<Employee> emplist=employeerepo.findAll();
		//List<Employee> desorder=emplist.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
		List<Employee> desorder=emplist.stream().sorted((e1,e2)->(int)(e1.getSalary()-e2.getSalary())).limit(3).collect(Collectors.toList());
		return new ResponseEntity<>(desorder,HttpStatus.OK);
	
	}
	//fetch the all employees who have the salary less than 3rd highest salary
	@GetMapping("/employeesWithlowsal")
	public ResponseEntity<?> employeesWithlowsal()
	{
		List<Employee> emp=employeerepo.findAll();
		List<Employee> thirdHiegstsal=emp.stream().sorted((e1,e2)->(int)(e1.getSalary()-e2.getSalary())).skip(3).collect(Collectors.toList());
		return new ResponseEntity<>(thirdHiegstsal,HttpStatus.OK);
	}
	

}
