package in.co.employee.employeestreaming.Model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fname;
	private String lname;
	private String department;
	private String designation;
	private Double salary;
	private int age;
	private int yearOfJoining;
	public int getYearOfJoining() {
		return yearOfJoining;
	}
	public void setYearOfJoining(int yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}
	private String gender;
	
	
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true; // Check if the objects are the same
	        if (o == null || getClass() != o.getClass()) return false; // Check for null and class type
	        Employee employee = (Employee) o; // Cast the object to Employee
	        return Objects.equals(id, employee.id); // Compare the id fields for equality
	    }

	  
		public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
		// Overriding hashCode() to ensure consistent hashing
	    @Override
	    public int hashCode() {
	        return Objects.hash(id); // Generate hash code based on the id field
	    }
	    @Override
		public String toString() {
			return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", department=" + department
					+ ", designation=" + designation + ", salary=" + salary + ", age=" + age + "]";
		}
	
	
	
	

}
