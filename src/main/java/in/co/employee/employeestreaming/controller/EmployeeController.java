package in.co.employee.employeestreaming.controller;

import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.co.employee.employeestreaming.Model.Employee;
import in.co.employee.employeestreaming.repository.EmployeeRepository;

@RestController
@RequestMapping("/streamingTest")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeerepository;

	// segregate each employee based on their age
/*	@GetMapping("/getEmployee")
	public ResponseEntity<?> getempbyage() {

		Map<String, List<Employee>> employeesByAge = employeerepository.findAll().stream().collect(Collectors.groupingBy(e->Optional.ofNullable(e.getAge()).orElse("null"), TreeMap::new,Collectors.toList()));
				
		System.out.println(employeesByAge.toString());
		return new ResponseEntity<>(employeesByAge, HttpStatus.OK);

	}
	@GetMapping("/getMaxAge")
	public ResponseEntity<?> getMaxAge()
	{
		List<String> elist = employeerepository.findAll().stream()
	            .map(emp -> Optional.ofNullable(emp.getAge()).orElse("0")) // Replace null with "0" or another default value
	            .collect(Collectors.toList());
	    
			IntSummaryStatistics summary=elist.stream().mapToInt(x->Integer.parseInt(x)).summaryStatistics();
		return new ResponseEntity<>(summary.getMax(),HttpStatus.OK);
		
	}
	//2md and 3rd youngest employee
	/*@GetMapping("/getyoungest")
	public ResponseEntity<?> getYoungest()
	{
		List<Employee> employees = employeerepository.findAll().stream()
			    .filter(e -> e.getAge() != null) // Filter out employees with null age
			    .sorted(Comparator.comparingInt(e -> Integer.parseInt(e.getAge()))) // Sort by age
			    .skip(1) // Skip the youngest employee
			    .limit(2) // Limit to the 2nd and 3rd youngest employees
			    .collect(Collectors.toList());
		
		
		return new ResponseEntity<>(employees,HttpStatus.OK);
		
	}*/
	
	@GetMapping("/joinName")
	public ResponseEntity<?> joinName()
	{
		String name=employeerepository.findAll().stream().map(e->Optional.ofNullable(e.getFname()).orElse(null)).collect(Collectors.joining(", "));
				return new ResponseEntity<>(name,HttpStatus.OK);
		
	}
	@GetMapping("/getDuplicates")
	public ResponseEntity<?> getDuplicates()
	{
		List<String> names=employeerepository.findAll().stream().map(e->Optional.ofNullable(e.getFname()).orElse(null))
				.collect(Collectors.toList());
		Map<String, Long> freq=names.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		List<String> duplicates=freq.entrySet().stream().filter(e->e.getValue()>1).map(e->e.getKey()).collect(Collectors.toList());
		return new ResponseEntity<>(duplicates,HttpStatus.OK);
		
		}
/*	//find nth higest salaryrepository.
	@GetMapping("/getSalary/{n}")
	public Optional<Object> getSalary(@PathVariable int n)
	{
		
		List<Float> salary = employeerepository.findAll()
    .stream()
    .map(e -> e.getSalary())
    .filter(Objects::nonNull)
    .collect(Collectors.toList());*/
       
 // Sort the list in descending order and find the nth highest salary
    /*Optional<Float> highestsalaryOptional = salary.stream()
        .sorted(Comparator.reverseOrder())
        .skip(n - 1)
        .findFirst();

    // Return the nth highest salary if present, otherwise return an error message
    return highestsalaryOptional
        .map(highestsalary -> new ResponseEntity<>(highestsalary, HttpStatus.OK));
      

	}*/
	
	/*//secregate employees by their salary
	@GetMapping("/getEmployeeBysalary")
	public ResponseEntity<?> getEmployeeBySalary(){
		 Map<Float, List<Employee>> salaryMap = employeerepository.findAll().stream()
		            .filter(Objects::nonNull)
		            .collect(Collectors.groupingBy(
		                    Employee::getSalary, //classifier method
		                    TreeMap::new,  //assending order
		                    Collectors.toList()   // Collect employees into lists
		            ));
		return new ResponseEntity<>(salaryMap,HttpStatus.OK);
		
	}*/
	//get employee whose age greater than 25
	@GetMapping("/getEmployeeGreaterthanage25")
	public ResponseEntity<?> getEmployeeGreaterthanage25(){
		 List<Integer> list = employeerepository.findAll().stream()
			        .map(emp -> Optional.ofNullable(emp.getAge())) // Wrap age in Optional
			        .filter(optAge -> optAge.isPresent() && optAge.get() > 25) // Check for present and condition
			        .map(Optional::get) // Unwrap the Optional
			        .collect(Collectors.toList());
		 return new ResponseEntity<>(list, HttpStatus.OK);
	}
	   String str = "preethi";
       String reversed = IntStream.range(0, str.length())
                                  .mapToObj(i -> String.valueOf(str.charAt(str.length() - 1 - i)))
                                  .collect(Collectors.joining());
       System.out.println(reversed);

		
//who has the most experience
	@GetMapping("/getExperiencedEmployee")
	public ResponseEntity<?> getExperiencedEmployee()
	{
		List<Employee> emp=employeerepository.findAll();
		Optional<Employee> senoiremployee=emp.stream().max(Comparator.comparingInt(Employee::getAge));
		Employee oldemp=senoiremployee.get();
		
		return new ResponseEntity<>(oldemp,HttpStatus.OK);
		
	}
	//how many males and females employees are there in the sales and marketting teams
	@GetMapping("/femalemale")
	public ResponseEntity<?> getbothemployee()
	{
		List<Employee> emplist=employeerepository.findAll();
		Map<String,Long> countmalefemale=emplist.stream().filter(emp->emp.getDepartment()=="sales and maerketting").collect(Collectors.groupingBy(e->e.getGender(),Collectors.counting()));
		return null;
		
	}
	
	//name of all employees who has joined in 2018
	@GetMapping("/getEmployee2018")
	public ResponseEntity<?> getEmployee2018()
	{
			List<Employee> l=employeerepository.findAll();
			List<String> names=l.stream().filter(emp->emp.getYearOfJoining()==2018).map(Employee::getFname).collect(Collectors.toList());
		return new ResponseEntity<>(names,HttpStatus.OK) ;
		
	}
	//get the details of highest paid employee
	@GetMapping("highestPaidEmployee")
	public ResponseEntity<?> highestPaidEmployee()
	{	
		List<Employee> l=employeerepository.findAll();
		Optional<Employee> emplist=l.stream().collect(Collectors.maxBy(Comparator.comparingDouble(e->e.getSalary())));
		return new ResponseEntity<>(emplist,HttpStatus.OK);
		
	}
	
	//print the name of all departments
	@GetMapping("/printdepartment")
	public ResponseEntity<?> printDepartmentname()
	{
		List<Employee> l1=employeerepository.findAll();
		l1.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
		return new ResponseEntity<>("sucess",HttpStatus.OK);
	}
	//how many male and female employees in the organizaztion
	@GetMapping("/countofmale and female")
	public ResponseEntity<?> getcountofgender()
	{
		List<Employee> emplist=employeerepository.findAll();
		Map<String, Long> freq=emplist.stream().collect(Collectors.groupingBy(e->e.getGender(),Collectors.counting()));
		return new ResponseEntity<>(freq,HttpStatus.OK);
	}
	

}
